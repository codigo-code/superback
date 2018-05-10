package ar.gob.gcaba.security.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import ar.gob.gcaba.cas.client.dao.IUsuarioLdapDao;
import ar.gob.gcaba.cas.client.domain.UsuarioLDAP;
import ar.gob.gcaba.security.common.domain.UserConverter;
import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.common.domain.UsuarioSolr;
import ar.gob.gcaba.security.common.ws.client.UsuarioRedisRestClientWS;
import ar.gob.gcaba.security.exceptions.RedisNegocioException;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
import ar.gob.gcaba.security.exceptions.SecurityNegocioException;
import ar.gob.gcaba.security.il.dao.IUsuarioDao;
import ar.gob.gcaba.security.il.dao.IUsuarioLdapSolrDAO;
import ar.gob.gcaba.security.il.dao.IUsuarioSolrDAO;
import ar.gob.gcaba.security.services.IUsuarioService;

@Service("usuarioService")
public final class UsuarioServiceImpl implements IUsuarioService {

	private static Logger logger = LoggerFactory.getLogger(IUsuarioService.class);	
	
	@Autowired
	protected IUsuarioDao usuarioDAO;
	@Autowired
	protected IUsuarioSolrDAO usuarioSolrDAO;
	
	@Autowired
	protected IUsuarioLdapSolrDAO usuarioSolrLdapDAO;

	@Autowired
	protected IUsuarioLdapDao ldapAccessor;

	@Autowired
	private UserConverter userConverter;
	
	
	protected Map <String, Usuario> listUsuarios = new HashMap<String, Usuario>();
	
	protected Map<String, Usuario> listUsuariosLdap = new HashMap<String, Usuario>();

	@Autowired
	private UsuarioRedisRestClientWS usuarioRedisRestClientWS;
	
	@Autowired
	private LdapUserSearch userSearch;
	
	@Autowired
	private LdapAuthoritiesPopulator authoritiesPopulator;
	
	private UserDetailsContextMapper userDetailsMapper = new LdapUserDetailsMapper();
	

	public List<Usuario> obtenerUsuariosPorSupervisor(String nombreUsuario) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getrUsuariosBySupervisor(nombreUsuario);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los datos del usuario", e);
		}
	}

	/**
	 * Busca por el campo username a todos sus supervisadoss
	 */
	//este servicio no es utilizado por el módulo de sescritorio ver, sino se utiliza por nadie eliminar
	@Override
	public List<UsuarioReducido> obtenerUsuariosDeSolrSupervisados(String userName) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getListaUsuarioReducidoBySuperior(userName);
			
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
		
	}

	/**
	 * Este metodo se utiliza como legacy de aplicaciones que siguen
	 * utilizandolo.
	 * 
	 */
	@Override
	public Usuario obtenerUsuario(String nombreUsuario) throws SecurityNegocioException {
		try {			
			int cantReintentos = 0;
			Usuario usuario = null;
			do {
				Thread.sleep(100);
				usuario = usuarioRedisRestClientWS.getUsuarioByNombreUsuario(nombreUsuario);
				cantReintentos++;
			} while ((usuario == null || (usuario !=null && !usuario.esUsuarioValido())) && cantReintentos < 5);
			
			if (usuario==null || (usuario !=null && !usuario.esUsuarioValido())){
				throw new org.springframework.security.core.userdetails.UsernameNotFoundException("No se ha podido cargar el Usuario: " + nombreUsuario);
			}
			return usuario;
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		} catch (InterruptedException e) {			
			throw new SecurityNegocioException(e.getMessage(), e);
		}
	}

	
	@Override
	public Usuario obtenerUsuarioSinFiltro(String nombreUsuario) throws SecurityNegocioException {
		try {
			int cantReintentos = 0;
			Usuario usuario = null;
			do {
				Thread.sleep(100);
				usuario = usuarioRedisRestClientWS.getUsuarioByNombreUsuarioSinFiltro(nombreUsuario);
				cantReintentos++;
			} while ((usuario == null || (usuario !=null && !usuario.esUsuarioValido())) && cantReintentos < 5);
			
			return usuario;
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(), e);
		} catch (InterruptedException e) {			
			throw new SecurityNegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	public List<UsuarioReducido> obtenerUsuariosPorRol(String rol) throws SecurityNegocioException{
		//se deja la implementacion que tiene con ldap
		return transformarUsuariosLDAP(ldapAccessor.buscarUsuariosPorRol(rol));
	}
	
	
	@Override
	public void indexarUsuario(String username) throws Exception{
		
		/*
		 * indexar informacion del usuario en redis 
		 */
		usuarioRedisRestClientWS.postIndexarUsuario(username);
		
		/*
		 * indexacion de solr 
		 */
		this.indexarUsuarioSolr(username);
		
		
	}
	
	private void indexarUsuarioSolr(String username) {

		try {
			
			Usuario usuario = usuarioDAO.obtenerUsuario(username);
			UsuarioSolr solr = new UsuarioSolr();
			
			List<UsuarioLDAP> usuariosLDAP = ldapAccessor.buscarUsuarioPorCn(username);
			
			
			if(usuariosLDAP != null && !usuariosLDAP.isEmpty() && usuario != null){
				
				usuario = mapearAtributosLDAP(usuario,usuariosLDAP.get(0));
				solr = userConverter.cargarDTO(usuario);
				usuarioSolrDAO.addToIndex(solr);
				return;
						
			}else if(usuariosLDAP!=null && !usuariosLDAP.isEmpty()){
				usuario = usuarioLdapToUsuario(usuariosLDAP.get(0));
				solr = userConverter.cargarDTO(usuario);
				usuarioSolrDAO.addToIndex(solr);
				return;
			}
			logger.info("EL USUARIO " + username + " NO SE PUDO INDEXAR EN SOLR");
		} catch (Exception e) {
			logger.error("Error al indexar usuario " + username + " a solr: " + e.getMessage());
		}
	}
	
	@Override
	public  void fullImportUsuariosActivos() {
		try {
			
			usuarioRedisRestClientWS.postFullImport();
			/**
			 * indexacion del usuario en solr
			 */
			long time_start, time_end;
			
			List<Usuario> listaUsuariosTmp = usuarioDAO.obtenerUsuarios();
			
			
			List<UsuarioLDAP> listaUsuarioLDAP = ldapAccessor.obtenerUsuariosLDAP();
			logger.info("LISTA DE USUARIO RECUPERADOS EN LDAP: " + listaUsuarioLDAP.size());
				
			time_start = System.currentTimeMillis();
			listaUsuariosTmp = this.depurarListaUsuarios(listaUsuariosTmp,listaUsuarioLDAP);
			time_end = System.currentTimeMillis();
			
			logger.info("TIEMPO DE EJECUCION DE DEPURACION DE LISTA USUARIO: "+ (time_end -time_start)+ "ms");
			
			time_start = System.currentTimeMillis();
			Collection<UsuarioSolr> usuarios = new ArrayList<UsuarioSolr>();
			for (Usuario usuario : listaUsuariosTmp) {
				UsuarioSolr userSolr = userConverter.cargarDTO(usuario);
				usuarios.add(userSolr);
			}
			time_end = System.currentTimeMillis();
			
			logger.info("TIEMPO DE PASAR LISTA USUARIO A LISTA USUARIO_SOLR: "+ (time_end -time_start)+ "ms");
			
			if(usuarioSolrDAO.indexarEnSolr(usuarios))logger.info(usuarios.size() + " usuarios indexados satisfactoriamente");

		} catch (Exception e) {
			logger.error("Error al cargar usuarios: " + e.getMessage(), e);
		}
	}
	
	@Override
	public boolean usuarioTieneRol(String userName, String rol) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getTieneRol(userName, rol);
		} catch (RedisNegocioException e) {
			logger.info(e.getMessage());
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}


	@Override
	public List<Usuario> obtenerUsuarios(String criterio) throws SecurityNegocioException {
		List<Usuario> rList = new ArrayList<Usuario>();
		try {
			rList = usuarioRedisRestClientWS.getListaUsuarioByCriterio(criterio);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener los usuarios por criterio", e);
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los usuarios por criterio", e);
		}
		return rList;
	}
	
	
	@Override
	public List<Usuario> obtenerUsuariosActivos(String criterio) throws SecurityNegocioException {
		List<Usuario> rList = new ArrayList<Usuario>();
		try {
			rList = usuarioRedisRestClientWS.getListaUsuarioByCriterioActivos(criterio);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener los usuarios por criterio", e);
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los usuarios por criterio", e);
		}
		return rList;
	}
	
	
	
	@Override
	public List<Usuario> obtenerUsuariosRegistrados(String criterio) throws SecurityNegocioException {
		List<Usuario> rList = new ArrayList<Usuario>();
		try {
			rList = usuarioRedisRestClientWS.getListaUsuarioByCriterioRegistrados(criterio);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener los usuarios por criterio", e);
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los usuarios por criterio", e);
		}
		return rList;
	}
	
	
	
	@Override
	public List<String> obtenerUsuariosRegistradosTexto(String criterio) throws SecurityNegocioException {
		List<String> rList = new ArrayList<String>();
		try {
			rList = usuarioRedisRestClientWS.getListaUsuarioByCriterioRegistradosTexto(criterio);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener los usuarios por criterio", e);
			throw new SecurityNegocioException("Ha ocurrido un error al obtener los usuarios por criterio", e);
		}
		return rList;
	}

	@Override
	public List<Usuario> obtenerUsuariosPorNombre(String nombre) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getByNombre(nombre);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}

	@Override
	public List<Usuario> obtenerUsuariosPorReparticion(String codigoReparticion) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getByReparticion(codigoReparticion);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}

	@Override
	public List<Usuario> obtenerUsuariosPorGrupo(String codigoReparticion, String sectorInterno)throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getByGrupo(codigoReparticion, sectorInterno);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}

	@Override
	public List<Usuario> obtenerUsuariosPorMesa(String codigoReparticion, String sectorMesa)throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getByMesa(codigoReparticion, sectorMesa);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}

	@Override
	public List<String> obtenerReparticionesHabilitadasPorUsuario(String nombreUsuario) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getReparticionesHabByUsuario(nombreUsuario);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener las reparticiones habilitadas del usuario", e);
			throw new SecurityNegocioException(
					"Ha ocurrido un error al obtener las reparticiones habilitadas del usuario", e);
		}
	}

	@Override
	public Boolean licenciaActiva(String usuario, Date fecha) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getLicenciaActiva(usuario);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException("Ha ocurrido un error al consultar la licencia del usuario", e);
		}
	}


	@SuppressWarnings("unused")
	private Usuario mapearDatosLdap(Usuario gcabaUser, UserDetails userDetails) {
		gcabaUser.setIsAccountNonExpired(userDetails.isAccountNonExpired());
		gcabaUser.setIsAccountNonLocked(userDetails.isAccountNonLocked());
		gcabaUser.setIsCredentialsNonExpired(userDetails.isCredentialsNonExpired());
		gcabaUser.setIsEnabled(userDetails.isEnabled());
		List<UsuarioLDAP> usuarios = ldapAccessor.buscarUsuarioPorCn(gcabaUser.getUsername());
		if (!usuarios.isEmpty()) {
			gcabaUser.setNombreApellido(usuarios.get(0).getNombreApellido());
			gcabaUser.setPermisos(ldapAccessor.obtenerPermisosPorUsuario(gcabaUser.getUsername()));
		}

		return gcabaUser;
	}
	/**
	 * mapeo de datos de UsuarioLDAP a Usuario -<b> se pone el usuario en inactivo</b>
	 * @param Usuario
	 * @param UsuarioLDAP
	 * @return Usuario
	 * @author carmarin
	 */
	private Usuario usuarioLdapToUsuario(UsuarioLDAP usuarioLDAP) {
		Usuario usuario = new Usuario();
		usuario.setIsAccountNonExpired(usuarioLDAP.getIsAccountNonExpired());
		usuario.setIsAccountNonLocked(usuarioLDAP.getIsAccountNonLocked());
		usuario.setIsCredentialsNonExpired(usuarioLDAP.getIsCredentialsNonExpired());
		usuario.setIsEnabled(usuarioLDAP.getIsEnabled());
		usuario.setNombreApellido(usuarioLDAP.getNombreApellido());
		usuario.setUsername(usuarioLDAP.getUsername());
		usuario.setPermisos(usuarioLDAP.getPermisos());
		usuario.setEmail(usuarioLDAP.getMail());
		usuario.setUsuarioActivo(false);
	
		return usuario;
	}
	
	/**
	 * mapeo los atributos de ldap al objeto
	 * @param Usuario
	 * @param UsuarioLDAP
	 * @return Usuario
	 * @author carmarin
	 */
	private Usuario mapearAtributosLDAP(Usuario usuario, UsuarioLDAP usuarioLDAP) {
		usuario.setIsAccountNonExpired(usuarioLDAP.getIsAccountNonExpired());
		usuario.setIsAccountNonLocked(usuarioLDAP.getIsAccountNonLocked());
		usuario.setIsCredentialsNonExpired(usuarioLDAP.getIsCredentialsNonExpired());
		usuario.setIsEnabled(usuarioLDAP.getIsEnabled());
		usuario.setNombreApellido(usuarioLDAP.getNombreApellido());
		usuario.setUsername(usuarioLDAP.getUsername());
		usuario.setPermisos(usuarioLDAP.getPermisos());
		return usuario;
	}

	@SuppressWarnings("unused")
	private List<Usuario> depurarListaUsuarios(List<Usuario> listaADepurar) {
		Vector<Usuario> toThrowAway = new Vector<Usuario>();
		if (listaADepurar != null) {

			List<UsuarioReducido> usuariosLdap = transformarUsuariosLDAP(ldapAccessor.buscarTodosUsuariosLdap());
			//List<UsuarioReducido> usuariosLdap = new ArrayList<UsuarioReducido>();
			ArrayList<UsuarioReducido> usuariosLdapSADE = new ArrayList<UsuarioReducido>();
			usuariosLdapSADE.addAll(usuariosLdap);

			Comparator<UsuarioReducido> comparator = new Comparator<UsuarioReducido>() {
				@Override
				public int compare(UsuarioReducido o1, UsuarioReducido o2) {
					return o1.compareTo(o2);
				}
			};
			Collections.sort(usuariosLdapSADE, comparator);
			for (Usuario usuario : listaADepurar) {
				int index = Collections.binarySearch(usuariosLdapSADE, usuario, comparator);

				if (index < 0) {
					toThrowAway.add(usuario);
				} else {
					if (StringUtils.isEmpty(usuario.getNombreApellido())) {
						List<UsuarioLDAP> lista = ldapAccessor.buscarUsuarioPorCn(usuario.getUsername());
						if (!lista.isEmpty()) {
							usuario.setNombreApellido(lista.get(0).getNombreApellido());
						}
					}
				}
			}
			// Eliminamos los que no están en el LDAP y si en la BBDD
			listaADepurar.removeAll(toThrowAway);
		}
		return listaADepurar;
	}
	
	private List<Usuario> depurarListaUsuarios(List<Usuario> listaADepurar,List<UsuarioLDAP> listaUsuarioLDAP) {
		
		List<Usuario> listaDepurada = new ArrayList<Usuario>();
		
		if (listaADepurar != null) {	
			ArrayList<Usuario> arraysUsuarioOrdenados = new ArrayList<Usuario>();
			arraysUsuarioOrdenados.addAll(listaADepurar);

			Comparator<Usuario> comparator = new Comparator<Usuario>() {
				@Override
				public int compare(Usuario o1, Usuario o2) {
					return o1.compareTo(o2);
				}
			};
			Collections.sort(arraysUsuarioOrdenados, comparator);
			
			for (UsuarioLDAP usuarioLDAP : listaUsuarioLDAP) {
				
				Usuario usuario = new Usuario();
				usuario.setUsername(usuarioLDAP.getUsername());
				
				int index = Collections.binarySearch(arraysUsuarioOrdenados, usuario, comparator);

				if (index >= 0) {
					usuario = arraysUsuarioOrdenados.get(index);
					usuario = mapearAtributosLDAP(usuario, usuarioLDAP);
				}else{
					usuario = this.usuarioLdapToUsuario(usuarioLDAP);
				}
				
				listaDepurada.add(usuario);
			}
		}
		return listaDepurada;
	}
	
	/**
	 * transforma los usuarios devuelto de ldap en usuariosReducido
	 * @param buscarTodosUsuariosLdap
	 * @return
	 */
	private List<UsuarioReducido> transformarUsuariosLDAP(List<UsuarioLDAP> listaUsuariosLDAP) {
		
		List<UsuarioReducido> listaUsuarioReducido = new ArrayList<UsuarioReducido>();
		for(UsuarioLDAP usuarioLDAP: listaUsuariosLDAP){
			 UsuarioReducido usuRed = new UsuarioReducido();
			 usuRed.setUsername(usuarioLDAP.getUsername());
			 usuRed.setNombreApellido(usuarioLDAP.getNombreApellido());
			 listaUsuarioReducido.add(usuRed);
		}
		
		return listaUsuarioReducido;
	}
	
	@Override
	public void cambiarPasswordUsuario(String username, String pwd) throws SecurityNegocioException {
		try {
			ldapAccessor.cambiarPasswordUsuario(username, pwd);
		} catch (Exception e) {
			throw new SecurityNegocioException(e.getMessage(), e);
		}
	}

	@Override
	/**
	 * permite cambiar la contraseña del usuario
	 * @param username
	 * @param password
	 * @return <b> true</b> si la contraseña fue validada con exito
	 * @throws SecurityNegocioException
	 */
	public Boolean validarPasswordUsuario(String username, String password) throws SecurityNegocioException {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("cn", username));
		try {
			return ldapAccessor.esPasswordUsuarioValido("", filter.encode(), password);
		} catch (Exception e) {
			throw new SecurityNegocioException(e.getMessage());
		}
	}
	
	@Override
	public Usuario obtenerUsuarioMigracion(String nombreUsuario)
			throws SecurityNegocioException {
			try {
				return usuarioRedisRestClientWS.getUsuarioMigracion(nombreUsuario);
			} catch (RedisNegocioException e) {
				logger.error("Ha ocurrido un error al consultar los usuarios", e);
				throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
			}
	}

	@Override
	public List<Usuario> obtenerUsuariosPorRevisor(String nombreUsuario) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getUsuarioByRevisor(nombreUsuario);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al consultar los usuarios", e);
			throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
		}
	}
	

	@Override
	public List<Usuario> obtenerUsuariosPorApoderado(String nombreUsuario)
			throws SecurityNegocioException {
		
		try {
			return usuarioRedisRestClientWS.getUsuarioByApoderado(nombreUsuario);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al consultar los usuarios", e);
			throw new SecurityNegocioException("Ha ocurrido un error al consultar los usuarios", e);
		}
	}

	@Override
	public void eliminarUsuario(String userName)
			throws SecurityNegocioException {
		
		try {
			try {
				/*eliminar un usuario en redis*/
				usuarioRedisRestClientWS.eliminarUsuario(userName);
			} catch (RedisNegocioException e) {
				logger.info(e.getMessage());
				throw new SecurityNegocioException(e.getMessage(),e);
			}
			/*eliminar un usuario en solr*/
			usuarioSolrDAO.eliminarUsuario(userName);
		} catch (SecurityAccesoDatosException e) {
			logger.error(e.getMessage(),e);
			throw new SecurityNegocioException("Ha ocurrido un error al eliminar el usuario", e);
		}
	}
	
	@Override
	public List<String> obtenerReparticionesAdministradasPorUsuario(String nombreUsuario) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getRepaAdministradaByNombreUsuario(nombreUsuario);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener las reparticiones administradas del usuario", e);
			throw new SecurityNegocioException(
					"Ha ocurrido un error al obtener las reparticiones administradas del usuario", e);
		}
	}
	
	
	@Override
	public Usuario obtenerUsuarioPorCuit(String cuit) throws SecurityNegocioException {
		try {
			return usuarioRedisRestClientWS.getListaUsuarioByCuit(cuit);
		} catch (RedisNegocioException e) {
			logger.info(e.getMessage());
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}
	
	@Override
	public List<Usuario> obtenerTodos(String cadenaBusqueda) throws SecurityNegocioException {
			
		try {
			return usuarioRedisRestClientWS.getObtenerTodos(cadenaBusqueda);
		} catch (RedisNegocioException e) {
			logger.info(e.getMessage());
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}
	
	@Override
	public List<Usuario> consultaUsuario(String cadenaBusqueda, String codigoRepa, String codigoSector) throws SecurityNegocioException {

		try {
			return usuarioRedisRestClientWS.getConsultaUsuario(cadenaBusqueda, codigoRepa, codigoSector);
		} catch (RedisNegocioException e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}
	
	
	@Override
	public Collection<GrantedAuthority> obtenerPermisosSegunNombreUsuario(Usuario usuario) throws SecurityNegocioException{
		try {
			UserDetails userLdap = obtenerUsuarioLdap(usuario.getUsername());
			return (Collection<GrantedAuthority>) userLdap.getAuthorities();
		} catch (Exception e) {
			throw new SecurityNegocioException(e.getMessage(),e);
		}
	}
	
	private UserDetails obtenerUsuarioLdap(String userName) {
		DirContextOperations dirContextOperations = userSearch.searchForUser(userName);
		Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>) authoritiesPopulator.getGrantedAuthorities(dirContextOperations, userName);
		UserDetails userDetails = userDetailsMapper.mapUserFromContext(dirContextOperations, userName, grantedAuthorities);

		return userDetails;
	}
	
	@Override
	public boolean reparticionSectorTieneUsuario(Integer sector,Integer reparticion) throws SecurityNegocioException{
		try {
			return usuarioRedisRestClientWS.reparticionSectorTieneUsuario(sector,reparticion);
		} catch (RedisNegocioException e) {
			logger.error("Ha ocurrido un error al obtener reparticion y sector", e);
			throw new SecurityNegocioException(
					"Ha ocurrido un error al obtener reparticion y sector", e);
		}
		
	}

	@Override
	public boolean sectorTieneUsuariosVigente(String codigoReparticion, String codigoSector)
			throws SecurityNegocioException {
		try {
		return usuarioRedisRestClientWS.sectorTieneUsuariosVigente(codigoReparticion,codigoSector);
		
	} catch (RedisNegocioException e) {
		logger.error("Ha ocurrido un error al obtener reparticion y sector", e);
		throw new SecurityNegocioException(
				"Ha ocurrido un error al obtener reparticion y sector", e);
	}
	}

	@Override
	public void establecerReparticionSeleccionada(Usuario usuario) throws Exception {
		
		usuarioRedisRestClientWS.postSetearReparticionSeleccionada(usuario);	
		
		/*
		 * indexacion de solr 
		 */
		this.indexarUsuarioSolr(usuario.getUsername());
	}
	
}
