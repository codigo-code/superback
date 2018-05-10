package ar.gob.gcaba.security.il.dao.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.jcs.JCS;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.gob.gcaba.security.common.Constantes;
import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.common.domain.UsuarioSolr;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
import ar.gob.gcaba.security.il.dao.IUsuarioSolrDAO;

@Repository("usuarioSolrDAO")
public class UsuarioSolrDAOImpl implements IUsuarioSolrDAO {

	
	@Autowired
	private SolrServer solrServerUSUARIOS;

	protected final transient Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean addToIndex(UsuarioSolr usuario) {
		boolean success = true;
		try {
			solrServerUSUARIOS.addBean(usuario);
			solrServerUSUARIOS.commit();
		} catch (MalformedURLException e) {
			logger.error("Error parsing URL address:" + e.getMessage(), e);
			success = false;
		} catch (Exception ex) {
			logger.error("Could not add to solr index", ex);
			success = false;
		}
		return success;
	}
	
	@Override
	public void limpiarIndice(){
		try{
			solrServerUSUARIOS.deleteByQuery("*:*");
			solrServerUSUARIOS.commit();			
		}catch(Exception e){
			logger.error("Error al limpiar el solr: " + e.getMessage());
		}
	}

	@Override
	public boolean addToIndex(Collection<UsuarioSolr> usuarios) {
		boolean success = true;
		try {
			solrServerUSUARIOS.addBeans(usuarios);
			solrServerUSUARIOS.commit();
		} catch (MalformedURLException e) {
			logger.error("Error parsing URL address:" + e.getMessage(), e);
			success = false;
		} catch (Exception ex) {
			logger.error("Could not add to solr index", ex);
			success = false;
		}
		return success;
	}

	@Override
	public boolean indexarEnSolr(Collection<UsuarioSolr> usuarios) throws SolrServerException, IOException {
		boolean success = true;
		try {
			solrServerUSUARIOS.deleteByQuery("*:*");
			solrServerUSUARIOS.addBeans(usuarios);
			solrServerUSUARIOS.commit();
		} catch (MalformedURLException e) {
			logger.error("Error parsing URL address:" + e.getMessage(), e);
			solrServerUSUARIOS.rollback();
			success = false;
		} catch (Exception ex) {
			solrServerUSUARIOS.rollback();
			logger.error("No se pudo indexar en solr: "+ex.getMessage(), ex);
			success = false;
		}
		return success;
	}

	@Override
	public UsuarioSolr searchByUsername(String username) throws SecurityAccesoDatosException {
		try {
			SolrQuery solrQuery = new SolrQuery(Constantes.OBTENER_USERNAME_SOLR + username);
			QueryResponse response;
			List<UsuarioSolr> list = new ArrayList<UsuarioSolr>();

			solrQuery.setRows(1); // Maximo 1 resultado!!!

			response = solrServerUSUARIOS.query(solrQuery);
			SolrDocumentList documents = response.getResults();
			UsuarioSolr reg = null;
			for (SolrDocument document : documents) {

				reg = convertirSolrDocument(document);
				list.add(reg);
			}
			return reg;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException("No se han podido recuperar los registro de SOLR", e);
		}
	}

	private UsuarioSolr convertirSolrDocument(SolrDocument document) {
		UsuarioSolr reg = new UsuarioSolr();
		
		reg.setUsername(this.<String> getFromDocument(Constantes.SOLR_FIELD_USERNAME, document));
		reg.setCodigoReparticion(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_REPARTICION, document));
		try {
			reg.setEmail(this.<String> getFromDocument(Constantes.SOLR_FIELD_EMAIL, document));
			reg.setNombreApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_APELLIDO, document));
		} catch (Exception e) {
			logger.info("Error el usuario no tiene indexado en ldap el mail o nombre y apellido");
		}
		if (reg.getCodigoReparticion()!=null){
		reg.setCodigoSectorInterno(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_SECTOR, document));
		reg.setCodigoSectorInternoOriginal(this.<String>getFromDocument(Constantes.SOLR_FIELD_CODIGO_SECTOR_ORIGINAL, document));
		reg.setCuit(this.<String> getFromDocument(Constantes.SOLR_FIELD_CUIT, document));
		reg.setEmail(this.<String> getFromDocument(Constantes.SOLR_FIELD_EMAIL, document));
		reg.setIsAccountNonExpired(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACCOUNT_NON_EXPIRED, document));
		reg.setIsAccountNonLocked(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACCOUNT_NON_LOCKED, document));
		reg.setIsCredentialsNonExpired(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_CREDENCIAL_NON_EXPIRED, document));
		reg.setIsEnabled(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ENABLED, document));
		reg.setIsMultireparticion(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_MULTIREPARTICION, document));
		reg.setSupervisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_SUPERVISOR, document));
		reg.setApoderado(this.<String> getFromDocument(Constantes.SOLR_FIELD_APODERADO, document));
		reg.setExternalizarFirmaGEDO(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_GEDO, document));
		reg.setExternalizarFirmaLOYS(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_LOYS, document));
		reg.setExternalizarFirmaCCOO(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_CCOO, document));
		reg.setExternalizarFirmaSIGA(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_SIGA, document));
		reg.setCargo(this.<String> getFromDocument(Constantes.SOLR_FIELD_CARGO, document));
		reg.setUsuarioRevisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_REVISOR, document));
		reg.setAceptacionTYC(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACEPTACION_TYC, document));
		reg.setSectorMesa(this.<String> getFromDocument(Constantes.SOLR_FIELD_SECTOR_MESA, document));
		reg.setCodigoReparticionOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_REPARTICION_ORIGINAL, document));
		reg.setNombreReparticionOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_REPARTICION_ORIGINAL, document));
		reg.setJurisdiccion(this.<String> getFromDocument(Constantes.SOLR_FIELD_JURISDICCION, document));
		if (reg.getIsMultireparticion()){
		reg.setJurisdiccionSeleccionada(this.<String>getFromDocument(Constantes.SOLR_FIELD_JURISDICCION_SELECCIONADA, document));}
		else{
			reg.setJurisdiccionSeleccionada(reg.getJurisdiccion());
		}}
		
		reg.setProvincia(this.<String> getFromDocument(Constantes.SOLR_FIELD_PROVINCIA, document));
		reg.setLocalidad(this.<String> getFromDocument(Constantes.SOLR_FIELD_LOCALIDAD, document));
		
		reg.setDepartamento(this.<String> getFromDocument(Constantes.SOLR_FIELD_DEPARTAMENTO, document));
		reg.setNombre(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE, document));
		reg.setApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_APELLIDO, document));
		reg.setNombreReparticion(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_REPARTICION, document));
		reg.setNombreApellidoSupervisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SUPERVISOR, document));
		reg.setNombreSectorInterno(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_INTERNO, document));
		reg.setNombreSectorInternoOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_INTERNO_ORGINAL, document));
		reg.setNombreApellidoApoderado(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_APODERADO, document));
		reg.setCargoOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_CARGO_ORIGINAL, document));
		reg.setSecretario(this.<String> getFromDocument(Constantes.SOLR_FIELD_SECRETARIO, document));	
		reg.setNombreApellidoSecretario(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECRETARIO, document));
		reg.setClavePublica(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_CLAVE_PUBLICA, document));
		reg.setNombreSectorMesa(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_MESA, document));
		reg.setValidarPolitica(this.<Boolean>getFromDocument(Constantes.SOLR_FIELD_VALIDAR_POLITICA, document));
		reg.setNotificarSolicitudPF(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_NOTIFICAR_SOLICITUD_PF, document));
		reg.setPermisos(this.<List<String>> getFromDocument(Constantes.SOLR_FIELD_PERMISOS, document));
		return reg;
	}
	
	private Usuario convertirSolrDocumentUsuario (SolrDocument document) {
		Usuario reg = new Usuario();
		

		reg.setUsername(this.<String> getFromDocument(Constantes.SOLR_FIELD_USERNAME, document));
		reg.setCodigoReparticion(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_REPARTICION, document));
		if (reg.getCodigoReparticion()!=null){
		reg.setCodigoSectorInterno(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_SECTOR, document));
		reg.setCodigoSectorInternoOriginal(this.<String>getFromDocument(Constantes.SOLR_FIELD_CODIGO_SECTOR_ORIGINAL, document));
		reg.setCuit(this.<String> getFromDocument(Constantes.SOLR_FIELD_CUIT, document));
		reg.setEmail(this.<String> getFromDocument(Constantes.SOLR_FIELD_EMAIL, document));
		reg.setIsAccountNonExpired(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACCOUNT_NON_EXPIRED, document));
		reg.setIsAccountNonLocked(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACCOUNT_NON_LOCKED, document));
		reg.setIsCredentialsNonExpired(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_CREDENCIAL_NON_EXPIRED, document));
		reg.setIsEnabled(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ENABLED, document));
		reg.setIsMultireparticion(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_MULTIREPARTICION, document));
		reg.setNombreApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_APELLIDO, document));
		reg.setSupervisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_SUPERVISOR, document));

		reg.setApoderado(this.<String> getFromDocument(Constantes.SOLR_FIELD_APODERADO, document));
		reg.setExternalizarFirmaGEDO(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_GEDO, document));
		reg.setExternalizarFirmaLOYS(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_LOYS, document));
		reg.setExternalizarFirmaCCOO(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_CCOO, document));
		reg.setExternalizarFirmaSIGA(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_EXT_SIGA, document));
		reg.setCargo(this.<String> getFromDocument(Constantes.SOLR_FIELD_CARGO, document));
		reg.setUsuarioRevisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_REVISOR, document));
		reg.setAceptacionTYC(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_ACEPTACION_TYC, document));
		reg.setSectorMesa(this.<String> getFromDocument(Constantes.SOLR_FIELD_SECTOR_MESA, document));
		reg.setCodigoReparticionOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_REPARTICION_ORIGINAL, document));
		reg.setNombreReparticionOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_REPARTICION_ORIGINAL, document));
		reg.setJurisdiccion(this.<String> getFromDocument(Constantes.SOLR_FIELD_JURISDICCION, document));
		if (reg.getIsMultireparticion() != null && reg.getIsMultireparticion()){
		reg.setJurisdiccionSeleccionada(this.<String>getFromDocument(Constantes.SOLR_FIELD_JURISDICCION_SELECCIONADA, document));}
		else{
			reg.setJurisdiccionSeleccionada(reg.getJurisdiccion());
		}}
		
		reg.setProvincia(this.<String> getFromDocument(Constantes.SOLR_FIELD_PROVINCIA, document));
		reg.setLocalidad(this.<String> getFromDocument(Constantes.SOLR_FIELD_LOCALIDAD, document));
		
		reg.setDepartamento(this.<String> getFromDocument(Constantes.SOLR_FIELD_DEPARTAMENTO, document));
		reg.setNombre(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE, document));
		reg.setApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_APELLIDO, document));
		reg.setApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_REPARTICION, document));
		reg.setNombreApellidoSupervisor(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SUPERVISOR, document));
		reg.setNombreSectorInterno(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_INTERNO, document));
		reg.setNombreSectorInternoOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_INTERNO_ORGINAL, document));
		reg.setNombreApellidoApoderado(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_APODERADO, document));
		reg.setCargoOriginal(this.<String> getFromDocument(Constantes.SOLR_FIELD_CARGO_ORIGINAL, document));
		reg.setSecretario(this.<String> getFromDocument(Constantes.SOLR_FIELD_SECRETARIO, document));	
		reg.setNombreApellidoSecretario(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECRETARIO, document));
		reg.setClavePublica(this.<Boolean> getFromDocument(Constantes.SOLR_FIELD_CLAVE_PUBLICA, document));
		reg.setNombreSectorMesa(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_SECTOR_MESA, document));
		reg.setValidarPolitica(this.<Boolean>getFromDocument(Constantes.SOLR_FIELD_VALIDAR_POLITICA, document));
		
		return reg;
	}

	@SuppressWarnings("unchecked")
	private <T> T getFromDocument(String fieldName, SolrDocument doc) {
		if (doc.get(fieldName) != null) {
			return (T) doc.get(fieldName);
		}
		return null;
	}

	@Override
	public List<UsuarioSolr> searchByQuery(String value) throws SecurityAccesoDatosException {
		try {
			SolrQuery solrQuery = new SolrQuery(value);
			QueryResponse response;
			List<UsuarioSolr> list = new LinkedList<UsuarioSolr>();

			solrQuery.setRows(100);

			response = solrServerUSUARIOS.query(solrQuery);
			SolrDocumentList documents = response.getResults();
			UsuarioSolr reg = null;
			for (SolrDocument document : documents) {
				reg = convertirSolrDocument(document);
				list.add(reg);
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException("No se han podido recuperar los registro de SOLR", e);
		}
	}
	@Override
	public Map<String, Usuario> obtenerTodos (JCS cacheUSU) throws SecurityAccesoDatosException {
		try {
			Map<String, Usuario> list = Collections.synchronizedMap(new WeakHashMap<String, Usuario>());
			if (cacheUSU==null){
			 
			//HashMap<String, Usuario> list = new HashMap<String, Usuario>();
			SolrDocumentList documents = obtenerDocumentosSolrPorQuery(Constantes.OBTENER_TODOS_SOLR);
			for (SolrDocument document : documents) {
				Usuario reg = convertirSolrDocumentUsuario(document);
				list.put(reg.getUsername(), reg);
				
			}}
			else{
				
			
				SolrDocumentList documents = obtenerDocumentosSolrPorQuery(Constantes.OBTENER_TODOS_SOLR);
				for (SolrDocument document : documents) {
					Usuario reg = convertirSolrDocumentUsuario(document);
					cacheUSU.put(reg.getUsername(), reg);
					
					list.put(reg.getUsername(), reg);
					
					
					
			}}
			return list; 
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"No se han podido recuperar los registro de SOLR", e);
		}
		
	}
	
	private SolrDocumentList obtenerDocumentosSolrPorQuery(String query) throws SolrServerException{
		SolrQuery solrQuery = new SolrQuery(query);
		solrQuery.setRows(5000000);
		QueryResponse response;
		response = solrServerUSUARIOS.query(solrQuery);
		return (response.getResults());
	}

	@Override
	public List<UsuarioReducido> searchByQueryUsuarioReducido(String query) throws SecurityAccesoDatosException {
		try {
			List<UsuarioReducido> list = new LinkedList<UsuarioReducido>();
			SolrDocumentList documents = obtenerDocumentosSolrPorQuery(query);
			for (SolrDocument document : documents) {		
				UsuarioReducido reg = new UsuarioReducido();
				reg.setUsername(this.<String> getFromDocument(Constantes.SOLR_FIELD_USERNAME, document));
				reg.setNombreApellido(this.<String> getFromDocument(Constantes.SOLR_FIELD_NOMBRE_APELLIDO, document));
				reg.setCodigoReparticion(this.<String> getFromDocument(Constantes.SOLR_FIELD_CODIGO_REPARTICION, document));
				list.add(reg);
			}
			return list;
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException("No se han podido recuperar los registro de SOLR", e);
		}
	}

	
	@Override
	public HashMap<String, Usuario> obtenerTodosConClaveNombreYApellido() throws SecurityAccesoDatosException {
		try {			
			HashMap<String, Usuario> list = new HashMap<String, Usuario>();
			SolrDocumentList documents = obtenerDocumentosSolrPorQuery(Constantes.OBTENER_TODOS_SOLR);
			for (SolrDocument document : documents) {
				Usuario reg = convertirSolrDocumentUsuario(document);
				list.put(reg.getNombreApellido(), reg);
			}
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"No se han podido recuperar los registro de SOLR", e);
		}
	}
	
	
	public void eliminarUsuario(String userName) throws SecurityAccesoDatosException{ 
		try {
			solrServerUSUARIOS.deleteById(userName);
			solrServerUSUARIOS.commit();
		} catch (SolrServerException e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"Error al eliminar registro de SOLR", e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"Error al eliminar registro de SOLR", e);
		}
	}
	
	
	@Override
	public UsuarioSolr searchByCuit(String cuit) throws SecurityAccesoDatosException {
		try {
			SolrQuery solrQuery = new SolrQuery(Constantes.OBTENER_USERNAME_CUIT_SOLR + cuit);
			QueryResponse response;
			List<UsuarioSolr> list = new ArrayList<UsuarioSolr>();

			solrQuery.setRows(1); // Maximo 1 resultado!!!

			response = solrServerUSUARIOS.query(solrQuery);
			SolrDocumentList documents = response.getResults();
			UsuarioSolr reg = null;
			for (SolrDocument document : documents) {

				reg = convertirSolrDocument(document);
				list.add(reg);
			}
			return reg;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SecurityAccesoDatosException("No se han podido recuperar los registro de SOLR", e);
		}
	}
}
