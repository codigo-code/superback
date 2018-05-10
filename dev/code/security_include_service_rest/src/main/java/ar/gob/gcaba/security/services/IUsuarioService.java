package ar.gob.gcaba.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.common.domain.UsuarioReducido;
import ar.gob.gcaba.security.exceptions.SecurityNegocioException;


/**
 * Provee informacion basica del usuario
 * 
 * @author javsolis
 */
public interface IUsuarioService {

	/**
	 * Obtiene los datos del usuario por userName.
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	public Usuario obtenerUsuario(String nombreUsuario) throws SecurityNegocioException;

	/**MiGRADO A REDIS - MERGEAR*
 * */
	
	public Usuario obtenerUsuarioSinFiltro(String nombreUsuario) throws SecurityNegocioException;
	/**
	 * Obtiene los datos del usuario por userName tipo like.
	 * 
	 * @param nombreUsuario
	 * @return lista de usuarios por encontrados
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 * 
	 */
	public List<Usuario> obtenerUsuarios(String criterio) throws SecurityNegocioException;
	
	
	/**
	 * Obtiene los datos del usuario activos por criterio
	 * 
	 * @param nombreUsuario
	 * @return lista de usuarios por encontrados
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 * 
	 */
	public List<Usuario> obtenerUsuariosActivos(String criterio) throws SecurityNegocioException;

	
	/**
	 * Obtiene los datos del usuario activos con TYC aceptados por criterio
	 * 
	 * @param nombreUsuario
	 * @return lista de usuarios por encontrados
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 * 
	 */
	public List<Usuario> obtenerUsuariosRegistrados(String criterio) throws SecurityNegocioException;

	/**
	 * Devuelve una lista de usuarios cuyo supervisor sea el indicado por
	 * parametro.
	 * 
	 * @param nombreUsuario
	 * @return lista de usuarios supervisados
	 * @throws SecurityNegocioException
	 *  ESTE METODO VA A LA BBBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public List<Usuario> obtenerUsuariosPorSupervisor(String nombreUsuario) throws SecurityNegocioException;

	/**
	 * Devuelve una lista con los codigos de reparticion habilitadas para operar
	 * por el usuario indicado por parametro
	 * 
	 * @param nombreUsuario
	 * @return codigos de reparticion
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A LA BBBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 * 
	 */
	public List<String> obtenerReparticionesHabilitadasPorUsuario(String nombreUsuario) throws SecurityNegocioException;


	/**
	 * Devuelve una lista de todos los usuarios
	 * @return
	 * @throws SecurityNegocioException 
	 * MiGRADO A REDIS - MERGEAR
	 */
	//TODO meto no necesitado en el modelo de EU
	//public Collection<Usuario> obtenerUsuarios() throws SecurityNegocioException;

	/**
	 * Determina si un usuario dado se encuentra de licencia en la fecha
	 * indicada
	 * 
	 * @param usuario
	 *            Usuario a validar
	 * @param fecha
	 *            Fecha de licencia
	 * @return true si esta de licencia, false si no
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A LA BBBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public Boolean licenciaActiva(String usuario, Date fecha) throws SecurityNegocioException;

	/**
	 * Determina si un usuario tiene el rol indicado
	 * 
	 * @param userName
	 * @param rol
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A LDAP POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	boolean usuarioTieneRol(String userName, String rol) throws SecurityNegocioException;

	/**
	 * Devuelve los username que tienen determinado rol
	 * 
	 * @param rol
	 *            por el cual se buscan los usuarios
	 * @return
	 * ESTE METODO VA A LDAP POR AHORA LO DEJAMOS ASI COMO ESTA
	 * @throws SecurityNegocioException 
	 */
	List<UsuarioReducido> obtenerUsuariosPorRol(String rol) throws SecurityNegocioException;

	/**
	 * Busca los usuarios por los campos username y nombre_apellido
	 * 
	 * @param nombre
	 *            username o nombre y apellido por el que se desea buscar
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	List<Usuario> obtenerUsuariosPorNombre(String nombre) throws SecurityNegocioException;

	/**
	 * Busca los usuarios dado un codigo de reparticion
	 * 
	 * @param codigoReparticion
	 * @return
	 * @throws SecurityNegocioException
	 * ARREGLAR EN REDIS Y MERGEAR
	 */
	List<Usuario> obtenerUsuariosPorReparticion(String codigoReparticion) throws SecurityNegocioException;

	/**
	 * Busca los usuarios dado un codigo de reparticion y un sector
	 * 
	 * @param codigoReparticion
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	List<Usuario> obtenerUsuariosPorGrupo(String codigoReparticion, String sectorInterno)
			throws SecurityNegocioException;

	/**
	 * Busca los usuarios dado un codigo de reparticion y un sector mesa
	 * 
	 * @param codigoReparticion
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	List<Usuario> obtenerUsuariosPorMesa(String codigoReparticion, String sectorMesa) throws SecurityNegocioException;
	
	/**
	 * Devuelve una lista de todos los usuarios que estan en Solr Supervisados por el usuario del parametro
	 * @param userNaem
	 * @return  Lista de usuariosReducidos
	 * @throws SecurityNegocioException 
	 * Mergear MIGRADO A REDIS //revisar si funciona / si se debe dejar
	 */
	public List<UsuarioReducido> obtenerUsuariosDeSolrSupervisados(String userName) throws SecurityNegocioException;
	
	/**
	 *  Permite cambiar el password del {@link Usuario} 
	 * @param username El username del {@link Usuario}
	 * @param pwd el password nuevo a modificar
	 * @throws SecurityNegocioException
	 *  ESTE METODO VA A LDAP POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	void cambiarPasswordUsuario(String username, String pwd) throws SecurityNegocioException;
	
	/**
	 * Indica true si el password corresponde al {@link Usuario} identificado por username
	 * 
	 * @param username el nombre del {@link Usuario}
	 * @param password el password a verificar
	 * @return true si hay correspondecia, de lo contrario false
	 * @throws ar.gob.gcaba.cas.client.exceptions.SecurityNegocioException 
	 *  ESTE METODO VA A LDAP POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	
	Boolean validarPasswordUsuario (String username, String password)throws SecurityNegocioException;
	
	/**
	 * Obtiene los datos del usuario por userName dado de baja.
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A BBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public Usuario obtenerUsuarioMigracion(String nombreUsuario) throws SecurityNegocioException;
	
	/**
	 * Obtiene los datos del usuario por userName por revisor.
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A BBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public List<Usuario> obtenerUsuariosPorRevisor(String nombreUsuario) throws SecurityNegocioException;
	
	
	
	/**
	 * Metodo de indexacion de un usuario partilar en Solr.
	 * SOLO debe usarse por EU en el contexto de active directory!!!!!!
	 * @param username
	 * @throws Exception
	 * MiGRADO A REDIS - MERGEAR
	 */
	public void indexarUsuario(String username) throws Exception;
	/**
	 * Obtiene los datos del usuario por userName por apoderado.
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A BBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public List<Usuario> obtenerUsuariosPorApoderado(String nombreUsuario) throws SecurityNegocioException;
	
	/**
	 * Eliminar del motor Solr el id por userName
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	public void eliminarUsuario(String userName) throws SecurityNegocioException;
	
	/**
	 * obtiene las reparticiones administradas
	 * @param nombreUsuario
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A BBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
	public List<String> obtenerReparticionesAdministradasPorUsuario(String nombreUsuario) throws SecurityNegocioException;

	/**
	 * Se genera el metodo para REdis v4.3.3*/

	public boolean sectorTieneUsuariosVigente(String codigoReparticion,
			String codigoSector) throws SecurityNegocioException;
	
	/**
	 * Realiza un full-import de los usuarios a SOLR
	 */
	//public void fullImportUsuariosLdap();
	
	
	/**
	 * Busqueda de usuario por cuit
	 * @param cuit
	 * @return
	 * @throws SecurityNegocioException
	 * MiGRADO A REDIS - MERGEAR
	 */
	public Usuario obtenerUsuarioPorCuit(String cuit) throws SecurityNegocioException;


	/**
	 * Obtiene los usuarios de acuerdo a un filtro determinado
	 * @param cadenaBusqueda
	 * @return List<Usuario>
	 * @throws SecurityNegocioException
	 * Estimar migracion a redis según documentacion
	 */
//	public List<Usuario> obtenerUsuariosDeSolrSegunFiltro(String cadenaBusqueda) throws SecurityNegocioException;

	/**
	 * Obtiene los usuarios de acuerdo a una Reparticion y/o Sector
	 * @param cadenaBusqueda 
	 * @param codigoRepa
	 * @param codigoSector
	 * @author panicola
	 * @return List<Usuario>
	 * @throws SecurityNegocioException
	 * Estimar migracion a redis según documentacion
	 */
	public List<Usuario> consultaUsuario(String cadenaBusqueda, String codigoRepa, String codigoSector) throws SecurityNegocioException;

	/**
	 * creo nuevo proceso de fullimport 
	 * @author carmarin
	 * MiGRADO A REDIS - MERGEAR
	 */
	public void fullImportUsuariosActivos();

	/**
	 * verifica los permisos del usuario
	 * @param usuario
	 * @throws SecurityNegocioException 
	 */
	public Collection<GrantedAuthority> obtenerPermisosSegunNombreUsuario(Usuario usuario) throws SecurityNegocioException;

	/**
	 * te devuelve una lista de usuarios activos del sistema
	 * @param cadenaBusqueda
	 * @return
	 * @throws SecurityNegocioException
	 * @author carmarin
	 * Estimar migracion a redis según documentacion
	 */
// 	public List<Usuario> obtenerUsuarioActivos(String cadenaBusqueda) throws SecurityNegocioException;

	/**
	 * Busco todos los usuarios tanto los activos como los inactivos
	 * @param cadenaBusqueda
	 * @return Lista de usuarios
	 * @throws SecurityNegocioException
	 * @author carmarin
	 * Estimar migracion a redis según documentacion
	 */
	public List<Usuario> obtenerTodos(String cadenaBusqueda) throws SecurityNegocioException;

	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws SecurityNegocioException
	 * ESTE METODO VA A BBDD POR AHORA LO DEJAMOS ASI COMO ESTA
	 */
		
	public boolean reparticionSectorTieneUsuario(Integer sector, Integer reparticion) throws SecurityNegocioException;

	/**
	 * Permitir actualiza la reparticion seleccionada por el usuario
	 * @param usuario
	 * @throws Exception
	 */
	public void establecerReparticionSeleccionada(Usuario usuario) throws Exception;
	/**
	 * 
	 * @param criterio
	 * @return
	 * @throws SecurityNegocioException
	 */

	public List<String> obtenerUsuariosRegistradosTexto(String criterio) throws SecurityNegocioException;

}