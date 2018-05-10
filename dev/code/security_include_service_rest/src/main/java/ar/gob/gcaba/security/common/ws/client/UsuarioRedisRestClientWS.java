package ar.gob.gcaba.security.common.ws.client;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.jcs.JCS;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.exceptions.RedisNegocioException;
import ar.gob.gcaba.security.services.impl.Utilitario;
import ar.gob.gde.exception.ws.client.RestResponseException;
import ar.gob.gde.ws.rest.client.RestClientWS;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

@Component
public class UsuarioRedisRestClientWS {

	private static Logger logger = LoggerFactory.getLogger(UsuarioRedisRestClientWS.class);

	public static final String SIN_DATOS = "SIN_DATOS";
	
	/**
	 * Busca la información de un usuario que se encuentre activo en el sistema
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException 
	 */
	public Usuario getUsuarioByNombreUsuario(String nombreUsuario) throws RedisNegocioException {
		Usuario usuario = null;
		try {

			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);

			usuario = RestClientWS.invoke(Usuario.class, RestClientRedisInformationEnum.GET_BY_USUARIO, reqParameters);
			return usuario;
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} de Redis: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));

			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}

	/**
	 * Busca la informacion del usuario sin restricción
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException 
	 */
	public Usuario getUsuarioByNombreUsuarioSinFiltro(String nombreUsuario) throws RedisNegocioException {
		Usuario usuario = null;
		try {

			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);

			usuario = RestClientWS.invoke(Usuario.class, RestClientRedisInformationEnum.GET_BY_USUARIO_SINFILTRO,
					reqParameters);
			
			return usuario;
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} de Redis: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}

	/**
	 * Indexar usuario en redis
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException
	 */
	public void postIndexarUsuario(String nombreUsuario) throws RedisNegocioException {
		try {

			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);

			RestClientWS.invoke(RestClientRedisInformationEnum.POST_INDEX_USUARIO, reqParameters);

		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al indexar usuario ${nombreUsuario} en Redis",
					ImmutableMap.of("nombreUsuario", nombreUsuario));

			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public void postSetearReparticionSeleccionada(Usuario usuario) throws RedisNegocioException{
		
		try {
			RestClientWS.invoke(RestClientRedisInformationEnum.POST_REPARTICION_SELECCIONADA, usuario);
		} catch (RestResponseException e) {
			
			String error = Utilitario.setParametroCadena("Error al setear la reparticion seleccionada en Redis",
					ImmutableMap.of("nombreUsuario", usuario.getUsername()));

			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);			
		}
		
	}
	
	/**
	 * paso la cahe para actualizar o generarla
	 * @param cacheUSU
	 * @return
	 * @throws RedisNegocioException
	 */
	public Map<String, Usuario> getListaUsuarioRedis(JCS cacheUSU) throws RedisNegocioException{
		
		try {
			Map<String, Usuario> list = Collections.synchronizedMap(new WeakHashMap<String, Usuario>());
			List<Usuario> listaUsuario = RestClientWS.invokeAndGetList(Usuario.class,
																		RestClientRedisInformationEnum.GET_LISTA_USUARIO,null);
			if (cacheUSU==null){
			 
				for (Usuario user : listaUsuario) {
					list.put(user.getUsername(), user);
					
				}
			}
			else{
				
				for (Usuario reg: listaUsuario) {
					cacheUSU.put(reg.getUsername(), reg);
					list.put(reg.getUsername(), reg);
					
				}
			}
			return list; 
		}catch(Exception e){
			logger.info("Error al obtener lista de usuario de redis: "+e.getMessage());
			throw new RedisNegocioException(e.getMessage(),e); 
		}
	}
	/**
	 * te devuelve un map de usuarios con clave el nombre y apellido
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException
	 */
	
	public Map<String, Usuario> getListaUsuarioRedis() throws RedisNegocioException{
		Map<String, Usuario> list = Collections.synchronizedMap(new WeakHashMap<String, Usuario>());
		try {
			List<Usuario> listaUsuario = RestClientWS.invokeAndGetList(Usuario.class,
																		RestClientRedisInformationEnum.GET_LISTA_USUARIO,null);
			for(Usuario us : listaUsuario){
				list.put(us.getNombreApellido(), us);
			}
			
			return list;
		} catch (RestResponseException e) {
			logger.info("Error al obtener lista de usuario de redis: "+e.getMessage());
			throw new RedisNegocioException(e.getMessage(),e); 
		}

		
	}
	
	public List<UsuarioReducido> getListaUsuarioReducidoBySuperior(String nombreUsuario) throws RedisNegocioException{
		List<UsuarioReducido> listaUsuario = null;
		try {

			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);

			listaUsuario = RestClientWS.invokeAndGetList(UsuarioReducido.class,
							RestClientRedisInformationEnum.GET_LISTA_USUARIO_REDUCIDO_BY_SUPERVISOR,reqParameters);
			return listaUsuario;
		
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} de Redis: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));

			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	public List<UsuarioReducido> getListaUsuarioReducido() throws RedisNegocioException{
		List<UsuarioReducido> listaUsuarioReducido = null;
		try {

			listaUsuarioReducido = RestClientWS.invokeAndGetList(UsuarioReducido.class,
							RestClientRedisInformationEnum.GET_LISTA_USUARIO_REDUCIDO,null);
			return listaUsuarioReducido;
		} catch (Exception e) {
			logger.info("Error al obtener lista de usuario reducidos de redis: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	public UsuarioReducido getUsuarioReducidoByNombreUsuario(String nombreUsuario) throws RedisNegocioException{
		UsuarioReducido usuario = null;
		
		MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
		reqParameters.add("nombreUsuario", nombreUsuario);
		
		try {
			usuario = RestClientWS.invoke(UsuarioReducido.class, RestClientRedisInformationEnum.GET_USUARIO_REDUCIDO,
					reqParameters);
			
			return usuario;
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} de Redis: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Indexar usuarios en redis
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException
	 */
	public void postFullImport() throws RedisNegocioException {
		try {

			RestClientWS.invoke(RestClientRedisInformationEnum.POST_FULLIMPORT_USUARIOS, null);

		} catch (Exception e) {
			logger.info("Error al realizar fullimport de usuarios en redis: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public List<Usuario> getListaUsuarioByCriterio(String criterioBusqueda) throws RedisNegocioException{
			
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("criterio", URLEncoder.encode(criterioBusqueda, "UTF-8"));
			
			return RestClientWS.invokeAndGetList(Usuario.class,RestClientRedisInformationEnum.GET_USUARIO_BY_CRITERIO_BUSQUEDA, reqParameters);
		} catch (Exception e) {
			
			logger.info("Error al consultar usuario por criterio: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	
	public List<Usuario> getListaUsuarioByCriterioActivos(String criterioBusqueda) throws RedisNegocioException{
		
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("criterio", URLEncoder.encode(criterioBusqueda, "UTF-8"));
			
			return RestClientWS.invokeAndGetList(Usuario.class,RestClientRedisInformationEnum.GET_USUARIO_BY_CRITERIO_BUSQUEDA_ACTIVOS, reqParameters);
		} catch (Exception e) {
			
			logger.info("Error al consultar usuario por criterio: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	
	public List<Usuario> getListaUsuarioByCriterioRegistrados(String criterioBusqueda) throws RedisNegocioException{
		
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("criterio", URLEncoder.encode(criterioBusqueda, "UTF-8"));
			
			return RestClientWS.invokeAndGetList(Usuario.class,RestClientRedisInformationEnum.GET_USUARIO_BY_CRITERIO_BUSQUEDA_REGISTRADOS, reqParameters);
		} catch (Exception e) {
			
			logger.info("Error al consultar usuario por criterio: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	
	public List<String> getListaUsuarioByCriterioRegistradosTexto(String criterioBusqueda) throws RedisNegocioException{
		
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("criterio", URLEncoder.encode(criterioBusqueda, "UTF-8"));
			
			return RestClientWS.invokeAndGetList(String.class,RestClientRedisInformationEnum.GET_USUARIO_BY_CRITERIO_BUSQUEDA_REGISTRADOS_TEXTO, reqParameters);
		} catch (Exception e) {
			
			logger.info("Error al consultar usuario por criterio: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public void eliminarUsuario(String nombreUsuario) throws RedisNegocioException{
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			RestClientWS.invoke(RestClientRedisInformationEnum.DELETE_ELIMINAR_USUARIO, reqParameters);			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} de Redis: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public Usuario getListaUsuarioByCuit(String cuit) throws RedisNegocioException{
		
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("cuit", cuit);
			
			return RestClientWS.invoke(Usuario.class,RestClientRedisInformationEnum.GET_USUARIO_BY_CUIT, reqParameters);
		} catch (Exception e) {
			
			logger.info("Error al consultar usuario por cuit: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	/**
	 * Te devuelve una lista de los supervisados de un usuario en particular
	 * @param nombreUsuario
	 * @return Lista de usuario
	 * @throws RedisNegocioException
	 * @author carmarin
	 */
	public List<Usuario> getrUsuariosBySupervisor(String nombreUsuario) throws RedisNegocioException{
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_LISTA_USUARIO_BY_SUPERVISOR, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener los supervisados de ${nombreUsuario}: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
			
		}
	}
	/**
	 * Cambio de contraseña
	 * @param nombreUsuario
	 * @param password
	 * @throws RedisNegocioException
	 * @author carmarin
	 */
	public void putCambiarPassword(String nombreUsuario, String password) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("username", nombreUsuario);
			reqParameters.add("pwd", URLEncoder.encode(password, "UTF-8"));
			RestClientWS.invoke(String.class, RestClientRedisInformationEnum.PUT_CAMBIAR_PASS, reqParameters,"vacio",null);

		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al modificar el pass de ${nombreUsuario}: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	/**
	 * valida si la contraseña ingresada es correcta
	 * @param nombreUsuario
	 * @param password
	 * @return <b> true</b> si la contraseña ingresada es correcta
	 * @throws RedisNegocioException
	 * @author carmarin
	 */
	public Boolean getValidarPassword(String nombreUsuario, String password) throws RedisNegocioException{
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("username", URLEncoder.encode(nombreUsuario, "UTF-8"));
			reqParameters.add("password", URLEncoder.encode(password, "UTF-8"));
			return RestClientWS.invoke(Boolean.class, RestClientRedisInformationEnum.GET_VALIDAR_PASS, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al validar el pass de ${nombreUsuario}: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		
		}
	}

	public Usuario getUsuarioMigracion(String nombreUsuario) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invoke(Usuario.class, RestClientRedisInformationEnum.GET_USUARIO_MIGRACION, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener el usuario migracion de ${nombreUsuario}: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public List<Usuario> getUsuarioByRevisor(String nombreUsuario) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_USUARIO_BY_REVISOR, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario ${nombreUsuario} por revisor: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public List<Usuario> getUsuarioByApoderado(String nombreUsuario) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_USUARIO_BY_APODERADO, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener usuario  ${nombreUsuario} por apoderado: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public List<String> getRepaAdministradaByNombreUsuario(String nombreUsuario) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invokeAndGetList(String.class, RestClientRedisInformationEnum.GET_REPA_ADMIN_BY_USUARIO, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener repa administrada del usuario  ${nombreUsuario} por apoderado: ",
					ImmutableMap.of("nombreUsuario", nombreUsuario));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	/**
	 * Te devuelve una lista de permiso del usuario
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException
	 */
	public Collection<GrantedAuthority>  getPermisosByNombreUsuario(Usuario usuario) throws RedisNegocioException{
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", usuario.getUsername());
			return RestClientWS.invokeAndGetList(GrantedAuthority.class, RestClientRedisInformationEnum.POST_PERMISOS_BY_USUARIO,reqParameters );
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al obtener los permisos del usuario  ${nombreUsuario} por apoderado: ",
					ImmutableMap.of("nombreUsuario", usuario.getUsername()));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public Boolean reparticionSectorTieneUsuario(Integer idSector, Integer idReparticion) throws RedisNegocioException{
		
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("sector",String.valueOf(idSector));
			reqParameters.add("reparticion", String.valueOf(idReparticion));
			return RestClientWS.invoke(Boolean.class, RestClientRedisInformationEnum.GET_REPA_SEC_TIENE_USUARIO, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al consultar el sector (${sectorId}) y repa (${reparticionId}) si tiene usuario: ",
					ImmutableMap.of("sectorId", idSector,"reparticionId",idReparticion));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	
public Boolean sectorTieneUsuariosVigente(String  codigoReparticion, String  codigoSector) throws RedisNegocioException{
		
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("codigoSectorInterno",codigoSector.replace("#", "%23"));
			reqParameters.add("codigoReparticion", codigoReparticion.replace("#", "%23"));
			return RestClientWS.invoke(Boolean.class, RestClientRedisInformationEnum.GET_REPA_SEC_TIENE_USUARIO_ACTIVO, reqParameters);
			
		} catch (Exception e) {
			String error = Utilitario.setParametroCadena("Error al consultar el sector (${sectorId}) y repa (${reparticionId}) si tiene usuario: ",
					ImmutableMap.of("sectorId", codigoSector,"reparticionId",codigoReparticion));
			
			logger.info(error + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
	}
	
	public List<Usuario> getObtenerTodos(String cadenaBusqueda) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("cadenaBusqueda", URLEncoder.encode(cadenaBusqueda, "UTF-8"));
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_OBTENER_TODOS, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener todos los usuarios: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Buscar los usuarios por la reparticion y el sector
	 * @param codigoReparticion
	 * @param codigoSector
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<Usuario> getByGrupo(String codigoReparticion, String codigoSector) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("codigoReparticion", codigoReparticion);
			reqParameters.add("sectorInterno", codigoSector);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_POR_GRUPO, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener los usuarios por su repa y sector: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * Buscar los usuarios por la reparticion y sector mesa
	 * @param codigoReparticion
	 * @param codigoSector
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<Usuario> getByMesa(String codigoReparticion, String codigoSectorMesa) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("codigoReparticion", codigoReparticion);
			reqParameters.add("sectorMesa", codigoSectorMesa);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_POR_MESA, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener los usuarios por su sector mesa: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * Buscar los usuarios por la reparticion
	 * @param codigoReparticion
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<Usuario> getByReparticion(String codigoReparticion) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("codigoReparticion", codigoReparticion);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_POR_REPARTICION, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener los usuarios por su repartición: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * Buscar los usuarios por la nombre
	 * @param nombre
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<Usuario> getByNombre(String nombre) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombre", nombre);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_POR_NOMBRE, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener los usuarios por su nombre: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Buscar los usuarios por su rol
	 * @param rol
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<UsuarioReducido> getByRol(String rol) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("rol", rol);
			return RestClientWS.invokeAndGetList(UsuarioReducido.class, RestClientRedisInformationEnum.GET_POR_ROL, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener los usuarios por su rol: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * Validar si el usuario tiene ese rol
	 * @param codigoReparticion
	 * @param codigoSector
	 * @return
	 * @throws RedisNegocioException
	 */
	public Boolean getTieneRol(String nombreUsuario, String rol) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("userName", nombreUsuario);
			reqParameters.add("rol", rol);
			return RestClientWS.invoke(Boolean.class, RestClientRedisInformationEnum.GET_TIENE_ROL, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al validar si el usurio tiene ese rol: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	/**
	 * Obtiene las repa habilitada de un usuario
	 * @param nombreUsuario
	 * @return
	 * @throws RedisNegocioException
	 */
	public List<String> getReparticionesHabByUsuario(String nombreUsuario) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("nombreUsuario", nombreUsuario);
			return RestClientWS.invokeAndGetList(String.class, RestClientRedisInformationEnum.GET_REPARTICIONES_HABILITADAS, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al obtener las repa habilitadas: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Obtiene las repa habilitada de un usuario
	 * @param usuario
	 * @return
	 * @throws RedisNegocioException
	 */
	public Boolean getLicenciaActiva(String usuario) throws RedisNegocioException{
		try {
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("usuario", usuario);
			return RestClientWS.invoke(Boolean.class, RestClientRedisInformationEnum.GET_LICENCIA_ACTIVA, reqParameters);
			
		} catch (Exception e) {			
			logger.info("Error al validar el usuario en licencia: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	/**
	 * Consulta los usuarios activos
	 * @param cadenaBusqueda
	 * @param codigoRepa
	 * @param codigoSector
	 * @return
	 * @throws RedisNegocioException
	 * @author carmarin
	 */
	@SuppressWarnings("static-access")
	public List<Usuario> getConsultaUsuario(String cadenaBusqueda, String codigoRepa, String codigoSector) throws RedisNegocioException{
		try {
			
			MultivaluedMap<String, String> reqParameters = new MultivaluedStringMap();
			reqParameters.add("cadenaBusqueda", StringUtils.isEmpty(cadenaBusqueda) ? this.SIN_DATOS : URLEncoder.encode(cadenaBusqueda, "UTF-8"));
			reqParameters.add("codigoRepa", StringUtils.isEmpty(codigoRepa) ? this.SIN_DATOS : codigoRepa);
			reqParameters.add("codigoSector", StringUtils.isEmpty(codigoSector) ? this.SIN_DATOS : codigoSector);
			return RestClientWS.invokeAndGetList(Usuario.class, RestClientRedisInformationEnum.GET_CONSULTA_USUARIO, reqParameters);
		} catch (Exception e) {			
			logger.info("Error al consultar los usuarios activos: " + e.getMessage());
			throw new RedisNegocioException(e.getMessage(), e);
		}
		
	}
	
	
	
}
