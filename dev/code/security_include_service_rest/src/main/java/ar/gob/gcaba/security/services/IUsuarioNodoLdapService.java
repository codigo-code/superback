package ar.gob.gcaba.security.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
import ar.gob.gcaba.security.exceptions.SecurityNegocioException;

	


/**
 * Interfaz para nodo Solr Ldap / busqueda desde el Administrador de EU
 * 
 * @author
 */
public interface IUsuarioNodoLdapService {
	
	
	public List<UsuarioReducido> obtenerUsuariosDeSolr() throws SecurityNegocioException;
	
	public void fullImportUsuarios();

	public List<Usuario> obtenerUsuariosDeSolrSegunFiltro(String cadenaBusqueda) throws SecurityNegocioException;
	
	public Collection<GrantedAuthority> obtenerPermisosSegunNombreUsuario(Usuario usuario);
	
	public void indexar(String nombreUsuario) throws SecurityNegocioException;

	public Usuario obtenerUsuarioPorCuit(String cuit) throws SecurityNegocioException;

	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) throws SecurityNegocioException;

	public List<Usuario> obtenerUsuarios() throws SecurityNegocioException, SecurityAccesoDatosException;
	
	/**
	 * se encarga dejar sola la informacion del usuario que se encuentra en ldap
	 * @author carmarin
	 * @param nombreUsuario
	 * @throws SecurityNegocioException
	 */
	public void restablecerUsuarioLdap(String nombreUsuario) throws SecurityNegocioException;
}