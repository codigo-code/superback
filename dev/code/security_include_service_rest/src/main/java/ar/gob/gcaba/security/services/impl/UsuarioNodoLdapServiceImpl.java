package ar.gob.gcaba.security.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import ar.gob.gcaba.cas.client.dao.IUsuarioLdapDao;
import ar.gob.gcaba.cas.client.domain.UsuarioLDAP;
import ar.gob.gcaba.cas.client.domain.UsuarioParcial;
import ar.gob.gcaba.security.common.Constantes;
import ar.gob.gcaba.security.common.domain.UserConverter;
import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.common.domain.UsuarioSolr;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
import ar.gob.gcaba.security.exceptions.SecurityNegocioException;
import ar.gob.gcaba.security.il.dao.IUsuarioDao;
import ar.gob.gcaba.security.il.dao.IUsuarioLdapSolrDAO;
import ar.gob.gcaba.security.services.IUsuarioNodoLdapService;
import ar.gob.gcaba.security.services.IUsuarioService;
		  
@Service("usuarioNodoLdapService")
public final class UsuarioNodoLdapServiceImpl implements IUsuarioNodoLdapService {

	private static Logger logger = LoggerFactory.getLogger(IUsuarioNodoLdapService.class);	

	@Autowired
	protected IUsuarioDao usuarioDAO;
	
	@Autowired
	protected IUsuarioLdapSolrDAO usuarioSolrLdapDAO;

	@Autowired
	protected IUsuarioLdapDao ldapAccessor;

	@Autowired
	private LdapUserSearch userSearch;

	@Autowired
	private LdapAuthoritiesPopulator authoritiesPopulator;

	@Autowired
	private UserConverter userConverter;

	protected Map<String, Usuario> listUsuarios = new HashMap<String, Usuario>();
	
	protected Map<String, Usuario> listUsuariosLdap = new HashMap<String, Usuario>();
	
	@Autowired
	private IUsuarioService usuarioService;
	
	private UserDetailsContextMapper userDetailsMapper = new LdapUserDetailsMapper();

	@Override
	public List<UsuarioReducido> obtenerUsuariosDeSolr() throws SecurityNegocioException {
		List<UsuarioReducido> listaUsuariosReducidos;
		try {
			listaUsuariosReducidos = usuarioSolrLdapDAO.searchByQueryUsuarioReducido(Constantes.OBTENER_TODOS_SOLR);
			return listaUsuariosReducidos;
		} catch (SecurityAccesoDatosException e) {
			logger.error("Ha ocurrido un error al consultar los usuarios", e);
			throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
		}
	}

	
	@Override
	public void fullImportUsuarios() {
		try {
			
			//Obtenemos los usuarios parciales (aun no tienen repa ni sector)
			List<UsuarioParcial> listaUsuariosParcialTmp = ldapAccessor.buscarTodosUsuariosParcialesLdap();
			
			List<Usuario> listaUsuariosTmp = new ArrayList<Usuario>();
			
			Collection<UsuarioSolr> usuarios = new ArrayList<UsuarioSolr>();
			
			List<UsuarioParcial> listUsuariosLdap;
			
			synchronized (this.listUsuariosLdap) {
				listUsuariosLdap = listaUsuariosParcialTmp;
			}
			
			for (UsuarioParcial usuarioParcial : listUsuariosLdap) {
				Usuario usuario = usuarioDAO.obtenerUsuarioCompleto(usuarioParcial.getUid());
				
				UsuarioSolr userSolr; 
				
				if(usuario!=null){
					UserDetails userLdap = obtenerUsuarioLdap(usuario.getUsername());
					usuario = mapearDatosLdap(usuario, userLdap);
					userSolr = userConverter.cargarDTO(usuario);
				}else{
					userSolr = userConverter.cargarDTO(usuarioParcial);
				}
				
				usuarios.add(userSolr);
				listaUsuariosTmp.add(usuario);
			}

			usuarioSolrLdapDAO.limpiarIndice();
			usuarioSolrLdapDAO.addToIndex(usuarios);
			logger.info(usuarios.size() + " usuarios indexados satisfactoriamente");

		} catch (Exception e) {
			logger.error("Error al cargar usuarios: " + e.getMessage(), e);
		}
	}
	
	private Usuario mapearDatosLdap(Usuario gcabaUser, UserDetails userDetails) {
		gcabaUser.setIsAccountNonExpired(userDetails.isAccountNonExpired());
		gcabaUser.setIsAccountNonLocked(userDetails.isAccountNonLocked());
		gcabaUser.setIsCredentialsNonExpired(userDetails.isCredentialsNonExpired());
		gcabaUser.setIsEnabled(userDetails.isEnabled());
		gcabaUser.setAuthorities(userDetails.getAuthorities());
		
		List<UsuarioLDAP> usuarios = ldapAccessor.buscarUsuarioPorCn(gcabaUser.getUsername());
		if (!usuarios.isEmpty()) {
			gcabaUser.setNombreApellido(usuarios.get(0).getNombreApellido());
			gcabaUser.setPermisos(ldapAccessor.obtenerPermisosPorUsuario(gcabaUser.getUsername()));
		}
		return gcabaUser;
	}
	
	private UserDetails obtenerUsuarioLdap(String userName) {
		DirContextOperations dirContextOperations = userSearch.searchForUser(userName);
		Collection<GrantedAuthority> grantedAuthorities = authoritiesPopulator.getGrantedAuthorities(
				dirContextOperations, userName);
		UserDetails userDetails = userDetailsMapper.mapUserFromContext(dirContextOperations, userName,
				grantedAuthorities);

		return userDetails;
	}
	
	@Override
	public List<Usuario> obtenerUsuariosDeSolrSegunFiltro(
			String cadenaBusqueda) throws SecurityNegocioException {
		
		String split[]= StringUtils.split(cadenaBusqueda);
		
		int factorial = Utilitario.factorial(split.length);
		
		List<String> listaPermutada = new ArrayList<String>();
		
		Utilitario.permutar(Arrays.asList(split), 0, factorial, listaPermutada);
		
		String parametro = Utilitario.armarParametroConsultaSolr(cadenaBusqueda,listaPermutada,split.length);
		
		List<Usuario> list = new ArrayList<Usuario>();

		String criterio = armarFiltroSOLR(Constantes.SOLR_FIELD_BUSQUEDA_USUARIO, parametro);
		list = obtenerUsuarios(criterio);
		
		return list;
	}
	
	
	private List<Usuario> obtenerUsuarios(String criterio) throws SecurityNegocioException {
		List<UsuarioSolr> list = new ArrayList<UsuarioSolr>();
		List<Usuario> rList = new ArrayList<Usuario>();
		try {
			list = usuarioSolrLdapDAO.searchByQuery(criterio);
		} catch (SecurityAccesoDatosException e) {
			logger.error("Ha ocurrido un error al obtener los usuarios por criterio", e);
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los usuarios por criterio", e);
		}

		for (UsuarioSolr usuarioSolr : list) {
			rList.add(userConverter.cargarUsuario(usuarioSolr));
		}
		return rList;
	}
	 
	 private String armarFiltroSOLR(String field, String value) {
			StringBuilder criterio = new StringBuilder(field);
			criterio.append(":");
			criterio.append(value);

			return criterio.toString();
	}


	@Override
	public Collection<GrantedAuthority> obtenerPermisosSegunNombreUsuario(Usuario usuario) {
		
		UserDetails userLdap = obtenerUsuarioLdap(usuario.getUsername());
		return userLdap.getAuthorities();
	}
	
	@Override
	public void indexar(String nombreUsuario) throws SecurityNegocioException {
		Usuario usuario;
		try {
			usuario = usuarioDAO.obtenerUsuario(nombreUsuario);
			UserDetails userLdap = obtenerUsuarioLdap(usuario.getUsername());
			
			usuario = mapearDatosLdap(usuario, userLdap);
			UsuarioSolr userSolr = userConverter.cargarDTO(usuario);
			usuarioSolrLdapDAO.addToIndex(userSolr);
		} catch (SecurityAccesoDatosException e) {
			throw new SecurityNegocioException(e);
		}
	}
	
	@Override
	public Usuario obtenerUsuarioPorCuit(String cuit) throws SecurityNegocioException {
		try {
			UsuarioSolr usuarioSolr = usuarioSolrLdapDAO.searchByCuit(cuit);
			Usuario gcabaUser = userConverter.cargarUsuario(usuarioSolr);
			
			return gcabaUser;
		} catch (SecurityAccesoDatosException e) {
			logger.error("Ha ocurrido un error al consultar los usuarios", e);
			throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
		}
	}
	
	
	@Override
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws SecurityNegocioException {
		try {
			UsuarioSolr usuarioSolr = usuarioSolrLdapDAO.searchByUsername(StringUtils.upperCase(nombreUsuario));
			Usuario gcabaUser = userConverter.cargarUsuario(usuarioSolr);
			
			return gcabaUser;
		} catch (SecurityAccesoDatosException e) {
			logger.error("Ha ocurrido un error al consultar los usuarios", e);
			throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
		}
	}
	
	@Override
	public List<Usuario> obtenerUsuarios() throws SecurityNegocioException{
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		try {
			Map<String, Usuario> listaMapUsuarios = usuarioSolrLdapDAO.obtenerTodos();
			
			for(Usuario u : listaMapUsuarios.values()){
				listaUsuario.add(u);
			}
			
		} catch (SecurityAccesoDatosException e) {
			throw new SecurityNegocioException(e.getMessage(), e);
		}
		

		return listaUsuario;
	}
	
	@Override
	public void restablecerUsuarioLdap(String nombreUsuario) throws SecurityNegocioException{
		try {
			usuarioSolrLdapDAO.eliminarUsuario(nombreUsuario);
			UsuarioParcial usuario = ldapAccessor.obtenerUsuarioParcial(nombreUsuario);
			 if(usuario!=null){
				 UsuarioSolr userSolr;
				 userSolr = userConverter.cargarDTO(usuario);
				 usuarioSolrLdapDAO.addToIndex(userSolr);
			 }
		} catch (Exception e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}
}
