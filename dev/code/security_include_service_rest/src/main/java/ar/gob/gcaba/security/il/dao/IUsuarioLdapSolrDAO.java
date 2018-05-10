package ar.gob.gcaba.security.il.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.common.domain.UsuarioSolr;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;

/**
 * Intefaz creada para copiar el contenido de Ldap en Solrm utilizado para busquedas
 * @author esroveda
 *
 */
public interface IUsuarioLdapSolrDAO {
	
	public UsuarioSolr searchByUsername (String username) throws SecurityAccesoDatosException;
	
	public List<UsuarioSolr> searchByQuery (String value) throws SecurityAccesoDatosException;

	public boolean addToIndex(UsuarioSolr usuario);

	public boolean addToIndex(Collection<UsuarioSolr> usuarios);

	HashMap<String, Usuario> obtenerTodos () throws SecurityAccesoDatosException;
	
	public List<UsuarioReducido> searchByQueryUsuarioReducido(String query) throws SecurityAccesoDatosException;

	public void limpiarIndice();
	
	public HashMap<String, Usuario> obtenerTodosConClaveNombreYApellido() throws SecurityAccesoDatosException;
	
	public void eliminarUsuario(String userName) throws SecurityAccesoDatosException;

	public UsuarioSolr searchByCuit(String cuit) throws SecurityAccesoDatosException;
}
