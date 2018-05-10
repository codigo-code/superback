package ar.gob.gcaba.security.common.ws.client;

import java.util.Properties;

import javax.ws.rs.core.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.gob.gde.ws.rest.client.RequestMethod;
import ar.gob.gde.ws.rest.client.RestClientInformation;

public enum RestClientRedisInformationEnum implements RestClientInformation{
	GET_BY_USUARIO_SINFILTRO(
			"api/v1/usuario/sinFiltro/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/sinFiltro/{nombreUsuario}",
			RequestMethod.METHOD_GET),
	
	GET_BY_USUARIO(
			"api/v1/usuario/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/{nombreUsuario}",
			RequestMethod.METHOD_GET),
	
	POST_INDEX_USUARIO(
			"api/v1/usuario/indexar/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/indexar/{nombreUsuario}",
			RequestMethod.METHOD_POST),
	
	POST_REPARTICION_SELECCIONADA(
			"api/v1/usuario/asignacion-reparticion-seleccionada",
			"http://localhost/redis-webclient/api/v1/usuario/asignacion-reparticion-seleccionada",
			RequestMethod.METHOD_POST),
	
	GET_LISTA_USUARIO(
			"api/v1/usuario/obtenerUsuarios",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerUsuarios",
			RequestMethod.METHOD_GET),
	
	GET_LISTA_USUARIO_REDUCIDO(
			"api/v1/usuario/obtenerDeSolr",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerDeSolr",
			RequestMethod.METHOD_GET),
	
	GET_USUARIO_REDUCIDO(
			"api/v1/usuario/obtenerReducido/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerReducido/{nombreUsuario}",
			RequestMethod.METHOD_GET),
	
	POST_FULLIMPORT_USUARIOS(
			"api/v1/usuario/fullImportUsuariosActivos",
			"http://localhost/redis-webclient/api/v1/usuario/fullImportUsuariosActivos",
			RequestMethod.METHOD_POST),
	
	GET_USUARIO_BY_CRITERIO_BUSQUEDA(
			"api/v1/usuario/criterio/{criterio}/",
			"http://localhost/redis-webclient/api/v1/usuario/criterio/{criterio}",
			RequestMethod.METHOD_GET),
	
	GET_USUARIO_BY_CRITERIO_BUSQUEDA_ACTIVOS(
			"api/v1/usuario/criterioActivos/{criterio}/",
			"http://localhost/redis-webclient/api/v1/usuario/criterioActivos/{criterio}",
			RequestMethod.METHOD_GET),
	
	GET_USUARIO_BY_CRITERIO_BUSQUEDA_REGISTRADOS(
			"api/v1/usuario/criterioRegistrados/{criterio}/",
			"http://localhost/redis-webclient/api/v1/usuario/criterioActivos/{criterio}",
			RequestMethod.METHOD_GET),
	
	GET_USUARIO_BY_CRITERIO_BUSQUEDA_REGISTRADOS_TEXTO(
			"api/v1/usuario/criterioRegistrados/texto/{criterio}/",
			"http://localhost/redis-webclient/api/v1/usuario/criterioActivos/{criterio}",
			RequestMethod.METHOD_GET),
	
	GET_LISTA_USUARIO_REDUCIDO_BY_SUPERVISOR(
			"api/v1/usuario/supervisor/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/supervisor/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	DELETE_ELIMINAR_USUARIO(
			"api/v1/usuario/eliminar/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/eliminar/{nombreUsuario}",
			RequestMethod.METHOD_DELETE
			),

	GET_USUARIO_BY_CUIT(
			"api/v1/usuario/porCuit/{cuit}/",
			"http://localhost/redis-webclient/api/v1/usuario/porCuit/{cuit}",
			RequestMethod.METHOD_GET
			),
	
	PUT_CAMBIAR_PASS(
			"api/v1/usuario/cambiarPasswordUsuario/{username}/pass/{pwd}/",
			"http://localhost/redis-webclient/api/v1/usuario/cambiarPasswordUsuario/{username}/pass/{pwd}",
			RequestMethod.METHOD_PUT
			),
	
	GET_VALIDAR_PASS(
			"api/v1/usuario/validarPasswordUsuario/{username}/pass/{password}/",
			"http://localhost/redis-webclient/api/v1/usuario/validarPasswordUsuario/{username}/pass/{password}",
			RequestMethod.METHOD_GET
			),
	
	GET_USUARIO_MIGRACION(
			"api/v1/usuario/validarPasswordUsuario/{username}/pass/{password}/",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerUsuarioMigracion/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	
	GET_USUARIO_BY_REVISOR(
			"api/v1/usuario/porRevisor/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/porRevisor/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	GET_USUARIO_BY_APODERADO(
			"api/v1/usuario/porApoderado/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/porApoderado/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	GET_REPA_ADMIN_BY_USUARIO(
			"api/v1/usuario/reparticionesAdministradas/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/reparticionesAdministradas/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	POST_PERMISOS_BY_USUARIO(
			"api/v1/usuario/obtenerPermisosSegunNombreUsuario/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerPermisosSegunNombreUsuario/{nombreUsuario}",
			RequestMethod.METHOD_POST
			),
	
	GET_REPA_SEC_TIENE_USUARIO(
			"api/v1/usuario/reparticionSectorTieneUsuario/{sector}/{reparticion}/",
			"http://localhost/redis-webclient/api/v1/usuario/reparticionSectorTieneUsuario/{sector}/{reparticion}",
			RequestMethod.METHOD_GET
			),
	
	GET_REPA_SEC_TIENE_USUARIO_ACTIVO(
			"api/v1/usuario/reparticionSectorTieneUsuarioActivo/{codigoReparticion}/{codigoSectorInterno}/",
			"http://localhost/redis-webclient/api/v1/usuario/reparticionSectorTieneUsuarioActivo/{codigoReparticion}/{codigoSectorInterno}",
			RequestMethod.METHOD_GET
			),
	
	GET_OBTENER_TODOS(
			"api/v1/usuario/obtenerTodos/{cadenaBusqueda}/",
			"http://localhost/redis-webclient/api/v1/usuario/obtenerTodos/{cadenaBusqueda}",
			RequestMethod.METHOD_GET),
	
	GET_POR_GRUPO(
			"api/v1/usuario/porGrupo/{codigoReparticion}/{sectorInterno}/",
			"http://localhost/redis-webclient/api/v1/usuario/porGrupo/{codigoReparticion}/{sectorInterno}",
			RequestMethod.METHOD_GET
			),
	
	GET_POR_MESA(
			"api/v1/usuario/porMesa/{codigoReparticion}/{sectorMesa}/",
			"http://localhost/redis-webclient/api/v1/usuario/porMesa/{codigoReparticion}/{sectorMesa}",
			RequestMethod.METHOD_GET
			),
	
	GET_POR_REPARTICION(
			"api/v1/usuario/porReparticion/{codigoReparticion}/",
			"http://localhost/redis-webclient/api/v1/usuario/porReparticion/{codigoReparticion}",
			RequestMethod.METHOD_GET
			),
	
	GET_POR_NOMBRE(
			"api/v1/usuario/conNombre/{nombre}/",
			"http://localhost/redis-webclient/api/v1/usuario/conNombre/{nombre}",
			RequestMethod.METHOD_GET
			),
	
	GET_POR_ROL(
			"api/v1/usuario/conRol/{rol}/",
			"http://localhost/redis-webclient/api/v1/usuario/conRol/{rol}",
			RequestMethod.METHOD_GET
			),
	
	
	GET_TIENE_ROL(
			"api/v1/usuario/{userName}/tieneRol/{rol}/",
			"http://localhost/redis-webclient/api/v1/usuario/{userName}/tieneRol/{rol}",
			RequestMethod.METHOD_GET
			),
	
	GET_REPARTICIONES_HABILITADAS(
			"api/v1/usuario/reparticiones/habilitadas/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/reparticiones/habilitadas/{nombreUsuario}",
			RequestMethod.METHOD_GET
			),
	
	GET_LICENCIA_ACTIVA(
			"api/v1/usuario/licenciaActiva/{usuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/licenciaActiva/{usuario}",
			RequestMethod.METHOD_GET
			),
	
	
	GET_CONSULTA_USUARIO(
			"api/v1/usuario/consultaUsuario/{cadenaBusqueda}/{codigoRepa}/{codigoSector}/",
			"http://localhost/redis-webclient/api/v1/usuario/consultaUsuario/{cadenaBusqueda}/{codigoRepa}/{codigoSector}",
			RequestMethod.METHOD_GET
			),
	
	GET_LISTA_USUARIO_BY_SUPERVISOR(
			"api/v1/usuario/supervisor/{nombreUsuario}/",
			"http://localhost/redis-webclient/api/v1/usuario/supervisor/{nombreUsuario}",
			RequestMethod.METHOD_GET
			);


	private String key;
	private String url;
	private RequestMethod method;
	private static final String SERVER_HOST_KEY = "usuario.redis.host";
	private static final Logger logger = LoggerFactory.getLogger(RestClientRedisInformationEnum.class);

	private RestClientRedisInformationEnum(String key, String url,
			RequestMethod method) {
		this.key = key;
		this.url = url;
		this.method = method;
	}

	public static void initialize(Properties properties) {
		logger.debug("Initializing properties");
		String serverHostValue = properties.getProperty(SERVER_HOST_KEY);
		if (serverHostValue == null){
			logger.warn("No se ha encontrado la property key: "
					+ SERVER_HOST_KEY);
		}else{
		

		
		for (RestClientRedisInformationEnum r : RestClientRedisInformationEnum.values()) {
			//String serviceValue = properties.getProperty(r.key);
			
			String urlValue = serverHostValue + r.key;
//
//			if (logger.isDebugEnabled())
//				logger.debug("Key: " + r.key + " - Value: " + serverHostValue
//						+ serviceValue);

			if (serverHostValue == null) {
				logger.warn("Se usa la url por default: " + r.getUrl()
						+ ", ya que no se ha podido formar la url externa: "
						+ urlValue);
				continue;
			}

			r.setUrl(urlValue);

		}}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.gob.ws.rest.client.RestClientInformation#getMethod()
	 */
	public RequestMethod getMethod() {
		return method;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
