package ar.gob.gcaba.security.il.dao;

import java.util.Date;
import java.util.List;

import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
	
/**
 * Interfaz para obtener usuarios de CCOO
 * 
 * @author psettino
 */
public interface IUsuarioDao {

    /**
     * Devuelve un usuario de CCOO
     * 
     * @param userName
     * @exception AccesoDatosException
     */
    public Usuario obtenerUsuario(String username)
	    throws SecurityAccesoDatosException;

    public List<Usuario> obtenerUsuariosPorSupervisor(String username)
	    throws SecurityAccesoDatosException;

    public List<Usuario> obtenerUsuarios()
	    throws SecurityAccesoDatosException;

	public int obtenerCantidadDeReparticionHabilitadaPorNombre(String username) throws SecurityAccesoDatosException;

	public List<String> obtenerReparticionesAdministradasPorNombreUsuario(String username) throws SecurityAccesoDatosException;
	
	public List<String> obtenerReparticionesHabilitadasPorNombreUsuario(String username) throws SecurityAccesoDatosException;

	public boolean licenciaActiva(String username, Date fecha) throws SecurityAccesoDatosException ;	
	
	public Usuario obtenerUsuarioMigracion(String username)throws SecurityAccesoDatosException;
	
	public List<Usuario> obtenerUsuariosPorRevisor(String username) throws SecurityAccesoDatosException;
	
	public List<Usuario> obtenerUsuariosPorApoderado(String nombreUsuario) throws SecurityAccesoDatosException;
	
	public int obtenerCantidadDeReparticionAdministradasPorNombre(String username)throws SecurityAccesoDatosException;
	
	public Usuario obtenerUsuarioCompleto(String username) throws SecurityAccesoDatosException;
	
	public boolean reparticionSectorTieneUsuario(Integer sector, Integer reparticion)throws SecurityAccesoDatosException;

}
