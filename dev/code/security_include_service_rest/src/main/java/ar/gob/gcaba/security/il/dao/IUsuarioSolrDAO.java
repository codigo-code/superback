package ar.gob.gcaba.security.il.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jcs.JCS;
import org.apache.solr.client.solrj.SolrServerException;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.common.domain.UsuarioSolr;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;


public interface IUsuarioSolrDAO {
	
	public UsuarioSolr searchByUsername (String username) throws SecurityAccesoDatosException;
	
	public List<UsuarioSolr> searchByQuery (String value) throws SecurityAccesoDatosException;

	public boolean addToIndex(UsuarioSolr usuario);

	public boolean addToIndex(Collection<UsuarioSolr> usuarios);

	public Map<String, Usuario> obtenerTodos (JCS cacheUSU) throws SecurityAccesoDatosException;
	
	public List<UsuarioReducido> searchByQueryUsuarioReducido(String query) throws SecurityAccesoDatosException;

	public void limpiarIndice();
	
	public HashMap<String, Usuario> obtenerTodosConClaveNombreYApellido() throws SecurityAccesoDatosException;
	
	public void eliminarUsuario(String userName) throws SecurityAccesoDatosException;
	
	public UsuarioSolr searchByCuit(String cuit) throws SecurityAccesoDatosException;
	
	/**
	 * indexar una lista de usuario en solr	</br>
	 * devuelve true si termino con existo sino false
	 * @author carmarin
	 * @param usuarios
	 * @return boolean
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public boolean indexarEnSolr(Collection<UsuarioSolr> usuarios) throws SolrServerException, IOException;
}
