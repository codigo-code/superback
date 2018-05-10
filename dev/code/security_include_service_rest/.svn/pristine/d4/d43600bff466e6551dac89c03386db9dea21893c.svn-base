package ar.gob.gcaba.security.il.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ar.gob.gcaba.cas.client.dao.IUsuarioLdapDao;
import ar.gob.gcaba.cas.client.domain.UsuarioLDAP;
import ar.gob.gcaba.security.common.domain.Usuario;
import ar.gob.gcaba.security.exceptions.SecurityAccesoDatosException;
import ar.gob.gcaba.security.il.dao.IUsuarioDao;
	

public class UsuarioDaoJdbc extends JdbcDaoSupport implements IUsuarioDao {

	@Autowired
	protected IUsuarioLdapDao ldapAccessor;

	private static final String QUERY_DE_JURISDICCION =			    " DECODE (NVL ( (SELECT 'NACION' FROM PROPERTY_CONFIGURATION  WHERE clave = 'eu.estructura.ministerio' AND ROWNUM=1),'CIUDAD'), 'NACION', (SELECT DECODE (NVL ((SELECT TO_CHAR (MINISTERIO) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (r.codigo_reparticion)) and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate),'NULO'),'NULO', ' ', (SELECT NOMBRE_REPARTICION FROM SADE_REPARTICION WHERE ID_REPARTICION =(NVL ((SELECT (MINISTERIO) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (r.codigo_reparticion))and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate), 1))))  FROM DUAL),               'CIUDAD',(SELECT DECODE (          NVL ((SELECT TO_CHAR (ID_JURISDICCION) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (r.codigo_reparticion)) and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate), 'NULO'), 'NULO', ' ',(SELECT DESCRIPCION FROM SADE_JURISdICCION WHERE ID = (NVL ( (SELECT (ID_JURISDICCION) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (r.codigo_reparticion))),1)))) FROM DUAL), (SELECT ' ' FROM DUAL))  jurisdiccion ";
	private static final String QUERY_DE_JURISDICCION_SELECCIONADA =" DECODE (NVL ( (SELECT 'NACION' FROM PROPERTY_CONFIGURATION  WHERE clave = 'eu.estructura.ministerio' AND ROWNUM=1),'CIUDAD'), 'NACION', (SELECT DECODE (NVL ((SELECT TO_CHAR (MINISTERIO) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (REPASEL.codigo_reparticion)) and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate),'NULO'),'NULO', ' ', (SELECT NOMBRE_REPARTICION FROM SADE_REPARTICION WHERE ID_REPARTICION =(NVL ((SELECT (MINISTERIO) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (REPASEL.codigo_reparticion))and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate), 1))))  FROM DUAL),               'CIUDAD',(SELECT DECODE (          NVL ((SELECT TO_CHAR (ID_JURISDICCION) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (REPASEL.codigo_reparticion)) and estado_registro = '1' and vigencia_desde < sysdate and vigencia_hasta > sysdate), 'NULO'), 'NULO', ' ',(SELECT DESCRIPCION FROM SADE_JURISdICCION WHERE ID = (NVL ( (SELECT (ID_JURISDICCION) FROM SADE_REPARTICION WHERE cODIGO_REPARTICION = RTRIM (LTRIM (REPASEL.codigo_reparticion))),1)))) FROM DUAL), (SELECT ' ' FROM DUAL))  jurisdiccion_seleccionada ";

	private static final String QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1 ="SELECT NVL2(REPASEL.codigo_reparticion, rtrim(ltrim(REPASEL.codigo_reparticion)), rtrim(ltrim(R.codigo_reparticion))) codigo_reparticion, "
			+ "NVL2(REPASEL.nombre_reparticion, rtrim(ltrim(REPASEL.nombre_reparticion)), rtrim(ltrim(R.nombre_reparticion))) nombre_reparticion,"
			+ "rtrim(ltrim(r.codigo_reparticion)) codigo_reparticion_original, rtrim(ltrim(r.nombre_reparticion)) nombre_reparticion_original,"
			+ "NVL2(REPASEL.codigo_sector_interno, rtrim(ltrim(REPASEL.codigo_sector_interno)), rtrim(ltrim(SI.codigo_sector_interno))) codigo_sector_interno,"
			+ "NVL2(REPASEL.nombre_sector_interno, rtrim(ltrim(REPASEL.nombre_sector_interno)), rtrim(ltrim(SI.nombre_sector_interno))) nombre_sector_interno,"
			+ "rtrim(ltrim(si.codigo_sector_interno)) codigo_sector_interno_original, rtrim(ltrim(si.nombre_sector_interno)) nombre_sector_interno_original,"
			+ "du.user_superior, (select ud.apellido_nombre from datos_usuario ud where ud.usuario = du.user_superior)  nombre_superior,"
			+ "du.usuario_asesor usuario_revisor,  (select ud.apellido_nombre from datos_usuario ud where ud.usuario = du.usuario_asesor)  nombre_revisor,"
			+ "du.ocupacion, du.usuario, du.mail,  du.secretario, (select ud.apellido_nombre from datos_usuario ud where ud.usuario = du.secretario) nombre_secretario,"
			+ "pe.apoderado,  (select ud.apellido_nombre from datos_usuario ud where ud.usuario = pe.apoderado) nombre_apoderado,"
			+ "(select cou.cargo from cargos cou where cou.id = du.cargo) cargo_original,"
			+ "DECODE (NVL (REPASEL.ID_CARGO, 0), REPASEL.ID_CARGO, DECODE (NVL (REPASEL.ID_CARGO, 0),NULL, NULL,(SELECT cargo FROM cargos WHERE id = repasel.id_cargo)),ca.cargo) cargo,"
			+ "du.aceptacion_tyc, rtrim(ltrim(DU.CODIGO_SECTOR_INTERNO)) SECTOR_MESA, "
			+ "(select nsi.nombre_sector_interno from sade_sector_interno nsi where nsi.id_sector_interno = du.id_sector_interno) nombre_sector_mesa,"
			+ "EXTERNALIZAR_FIRMA_EN_GEDO, EXTERNALIZAR_FIRMA_EN_SIGA, EXTERNALIZAR_FIRMA_EN_CCOO, EXTERNALIZAR_FIRMA_EN_LOYS,NUMERO_CUIT,  APELLIDO_NOMBRE, du.NOMBRE, du.APELLIDO,"
			+ "NVL2(MR.NOMBRE_USUARIO, 1, 0) as IS_MULTIREPARTICION, NOTIFICAR_SOLICITUD_PF, "
			+  QUERY_DE_JURISDICCION +", "+ QUERY_DE_JURISDICCION_SELECCIONADA +", "
			+ "PROV.NOMBRE provincia, LOC.NOMBRE localidad, "
			+ "(select dep.nombre from eu_departamento dep where  dep.id=loc.id_departamento) departamento, "
			+ "(case when du.token_clave_publica is not null then  1 else 0 end) clave_publica, "
			+ "(case when (su.estado_registro = 1  AND si.estado_registro = 1 and r.estado_registro = 1 AND r.vigencia_desde<=sysdate AND r.vigencia_hasta >=sysdate AND si.vigencia_desde <= sysdate AND si.vigencia_hasta >= sysdate) then  1 else 0 end) usuario_activo, "
			+ "du.validar_politica "
			+ "FROM ";
	

	private static final String TABLA_DATOS_USUARIO = " datos_usuario ";

	private static final String TABLA_DATOS_USUARIO_REVISION = " datos_usuario_hist ";
	
	private static final String QUERY_REPARTICION_SECTOR_TIENE_USUARIO = "SELECT COUNT(*) "
			+ "FROM SADE_USR_REPA_HABILITADA " + "WHERE ID_REPARTICION = ? "
			+ "AND ID_SECTOR_INTERNO = ?";
	
	private static final String QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2 = " du INNER JOIN sade_sector_usuario su ON DU.USUARIO = SU.NOMBRE_USUARIO "
			+ "LEFT JOIN eu_provincia prov ON du.id_provincia = prov.id "
			+ "LEFT JOIN eu_localidad loc ON du.id_localidad = loc.id " 
			+ "INNER JOIN sade_sector_interno si ON si.id_sector_interno=su.id_sector_interno "
			+ "INNER JOIN sade_reparticion r ON si.codigo_reparticion=r.id_reparticion "
			+ "LEFT JOIN CARGOS CA  ON ca.id = du.cargo "
			+ "LEFT JOIN (select usuario, apoderado "
			+ "from periodo_licencia pe "
			+ "where pe.fecha_Hora_Desde <= sysdate "
			+ "and pe.fecha_Hora_Hasta >= sysdate "
			+ "and pe.condicion_Periodo <> 'terminado' "
			+ "and pe.fecha_cancelacion is null) pe ON  du.usuario = pe.usuario "
			+ "LEFT JOIN ( "
			+ "SELECT NOMBRE_USUARIO FROM SADE_USR_REPA_HABILITADA "
			+ "GROUP BY NOMBRE_USUARIO "
			+ ") MR ON MR.NOMBRE_USUARIO = DU.USUARIO "
			+ "	 LEFT JOIN ("
			+ "    SELECT NOMBRE_USUARIO, SR.CODIGO_REPARTICION, NOMBRE_REPARTICION, CODIGO_SECTOR_INTERNO, SSI.NOMBRE_SECTOR_INTERNO, id_cargo FROM SADE_REPARTICION_SELECCIONADA RS "
			+ "    INNER JOIN SADE_REPARTICION SR ON RS.ID_REPARTICION = SR.ID_REPARTICION "
			+ "	INNER JOIN SADE_SECTOR_INTERNO SSI ON RS.ID_SECTOR_INTERNO = SSI.ID_SECTOR_INTERNO "
			+ "  ) REPASEL ON REPASEL.NOMBRE_USUARIO = DU.USUARIO ";
	
	private static final String QUERY_DATOSUSUARIO__PARTE2_TODOS = " du INNER JOIN sade_sector_usuario su ON DU.USUARIO = SU.NOMBRE_USUARIO "
			+ "LEFT JOIN eu_provincia prov ON du.id_provincia = prov.id "
			+ "LEFT JOIN eu_localidad loc ON du.id_localidad = loc.id " 
			+ "INNER JOIN sade_sector_interno si ON si.id_sector_interno=su.id_sector_interno "
			+ "INNER JOIN sade_reparticion r ON si.codigo_reparticion=r.id_reparticion "
			+ "LEFT JOIN CARGOS CA  ON ca.id = du.cargo "
			+ "LEFT JOIN (select usuario, apoderado "
			+ "from periodo_licencia pe "
			+ "where pe.fecha_Hora_Desde <= sysdate "
			+ "and pe.fecha_Hora_Hasta >= sysdate "
			+ "and pe.condicion_Periodo <> 'terminado' "
			+ "and pe.fecha_cancelacion is null) pe ON  du.usuario = pe.usuario "
			+ "LEFT JOIN ( "
			+ "SELECT NOMBRE_USUARIO FROM SADE_USR_REPA_HABILITADA "
			+ "GROUP BY NOMBRE_USUARIO "
			+ ") MR ON MR.NOMBRE_USUARIO = DU.USUARIO "
			+ "	 LEFT JOIN ("
			+ "    SELECT NOMBRE_USUARIO, SR.CODIGO_REPARTICION, NOMBRE_REPARTICION, CODIGO_SECTOR_INTERNO, SSI.NOMBRE_SECTOR_INTERNO, id_cargo FROM SADE_REPARTICION_SELECCIONADA RS "
			+ "    INNER JOIN SADE_REPARTICION SR ON RS.ID_REPARTICION = SR.ID_REPARTICION "
			+ "	INNER JOIN SADE_SECTOR_INTERNO SSI ON RS.ID_SECTOR_INTERNO = SSI.ID_SECTOR_INTERNO "
			+ "  ) REPASEL ON REPASEL.NOMBRE_USUARIO = DU.USUARIO ";
	
	private static final String QUERY_TIENE_REPARTICIONES_HABILITADAS_USUARIO = "SELECT COUNT(*) "
			+ "FROM SADE_USR_REPA_HABILITADA " + "WHERE NOMBRE_USUARIO = ?";

	private static final String QUERY_REPARTICIONES_HABILITADAS_USUARIO = "SELECT CODIGO_REPARTICION "
			+ "FROM SADE_USR_REPA_HABILITADA RH INNER JOIN SADE_REPARTICION R "
			+ "ON RH.ID_REPARTICION = R.ID_REPARTICION "
			+ "WHERE NOMBRE_USUARIO = ?";
	
	private static final String QUERY_TIENE_REPARTICIONES_ADMINISTRADAS_USUARIO = "SELECT COUNT(*) "
			+ "FROM SADE_USR_REPA_ADMINISTRADAS " + "WHERE NOMBRE_USUARIO = ?";
	
	private static final String QUERY_REPARTICIONES_ADMINISTRADAS_USUARIO = "SELECT CODIGO_REPARTICION "
			+ "FROM SADE_ADMIN_REPARTICION RH INNER JOIN SADE_REPARTICION R "
			+ "ON RH.ID_REPARTICION = R.ID_REPARTICION "
			+ "WHERE NOMBRE_USUARIO = ?";

	private static final String FILTRO_USERNAME_SUPERVISADO = "du.user_superior = ?";

	private static final String FILTRO_USERNAME_REVISOR = "du.usuario_asesor = ?";

	private static final String FILTRO_USERNAME_APODERADO = "pe.apoderado = ?";
	
	private static final String FILTRO_WHERE = " WHERE ";
	
	private static final String FILTRO_SERGYV = "du.USER_SUPERIOR not like 'SERGIO' and  du.MAIL not like 'muzychuks87@gmail.com' "
			+ "and du.USER_SUPERIOR not like 'SERGIO' and  du.MAIL not like 'muzychuks87@gmail.com' ";
		
	private static final String FILTRO_USERNAME = "su.nombre_usuario = ? ";
	
	private static final String FILTRO_ADD_AND = "AND ";
	
	private static final String FILTRO_ESTADO_REPA ="su.estado_registro = 1 ";

	private static final String QUERY_TIENE_LICENCIA = "SELECT COUNT(*) FROM PERIODO_LICENCIA "
			+ "WHERE PERIODO_LICENCIA.USUARIO = ? AND TO_CHAR(PERIODO_LICENCIA.FECHA_HORA_DESDE, 'YYYY-MM-DD HH24:MI:SS') <= ? "
			+ "AND TO_CHAR(NVL(PERIODO_LICENCIA.FECHA_CANCELACION,PERIODO_LICENCIA.FECHA_HORA_HASTA) , 'YYYY-MM-DD HH24:MI:SS') >= ? AND CONDICION_PERIODO <> ?";

	private static final String FILTRO_ULTIMA_REVISION = " AND du.revision = (select max(revision) FROM DATOS_USUARIO_HIST d where d.usuario like ? ) ";

	private Usuario mapUsuarioBean(Map<String, Object> row) {
		Usuario usuario = new Usuario();

		usuario.setUsername((String) row.get("usuario"));
		usuario.setEmail((String) row.get("mail"));
		usuario.setNombreApellido((String) row.get("apellido_nombre"));
		usuario.setSupervisor((String) row.get("user_superior"));

		usuario.setCodigoReparticion((String) row.get("codigo_reparticion"));
		usuario.setNombreReparticion((String) row.get("nombre_reparticion"));
		usuario.setCodigoReparticionOriginal((String) row.get("codigo_reparticion_original"));
		usuario.setCuit((String) row.get("numero_cuit"));
		usuario.setApoderado((String) row.get("apoderado"));

		usuario.setCodigoSectorInterno((String) row.get("codigo_sector_interno"));

		usuario.setSectorMesa((String) row.get("sector_mesa"));

		usuario.setOcupacion((String) row.get("ocupacion"));
		usuario.setNombreReparticionOriginal((String) row.get("nombre_reparticion_original"));
		usuario.setUsuarioRevisor((String) row.get("usuario_revisor"));

		usuario.setCargo((String) row.get("cargo"));
		usuario.setCargoOriginal((String) row.get("cargo_original"));
		usuario.setCodigoSectorInternoOriginal((String) row.get("codigo_sector_interno_original"));

		usuario.setJurisdiccion((String) row.get("jurisdiccion"));
		usuario.setJurisdiccionSeleccionada((String) row.get("jurisdiccion_seleccionada"));
		
		usuario.setProvincia((String) row.get("provincia"));
		usuario.setLocalidad((String) row.get("localidad"));
	
		usuario.setNombreApellidoSupervisor((String) row.get("nombre_superior"));
		usuario.setNombreApellidoApoderado((String)row.get("nombre_apoderado"));
		usuario.setDepartamento((String)row.get("departamento"));
		usuario.setNombre((String) row.get("nombre"));
		usuario.setApellido((String) row.get("apellido"));
		usuario.setNombreSectorInternoOriginal((String) row.get("nombre_sector_interno_original"));	
		usuario.setNombreApellidoUsuarioRevisor((String) row.get("nombre_revisor"));
		usuario.setSecretario((String) row.get("secretario"));
		usuario.setNombreApellidoSecretario((String) row.get("nombre_secretario"));
		usuario.setNombreSectorMesa((String) row.get("nombre_sector_mesa"));
		usuario.setNombreSectorInterno((String) row.get("nombre_sector_interno"));
		
		String notificar = (String) row.get("NOTIFICAR_SOLICITUD_PF");
		usuario.setNotificarSolicitudPF(false);
		if (notificar.equals("1")) {
			usuario.setNotificarSolicitudPF(true);
		}
		
		String val_pol = (String) row.get("validar_politica");
		usuario.setValidarPolitica(false);
		if ( val_pol!= null) {
			if (val_pol.equals("1")) {
				usuario.setValidarPolitica(true);
			}
		}

		if (StringUtils.isBlank(usuario.getNombreApellido())) {
			List<UsuarioLDAP> usuarios = ldapAccessor.buscarUsuarioPorCn(usuario.getUsername());
			if (!usuarios.isEmpty()) {
				usuario.setNombreApellido(usuarios.get(0).getNombreApellido());
			}
		}

		BigDecimal big1 = new BigDecimal(1);
		boolean gedo = false;
		if (row.get("externalizar_firma_en_gedo") != null) {
			if (((BigDecimal) row.get("externalizar_firma_en_gedo"))
					.equals(big1)) {
				gedo = true;
			}
		}
		usuario.setExternalizarFirmaGEDO(gedo);
		boolean ccoo = false;
		if (row.get("externalizar_firma_en_ccoo") != null) {
			if (((BigDecimal) row.get("externalizar_firma_en_ccoo"))
					.equals(big1)) {
				ccoo = true;
			}
		}
		usuario.setExternalizarFirmaCCOO(ccoo);
		boolean siga = false;
		if (row.get("externalizar_firma_en_siga") != null) {
			if (((BigDecimal) row.get("externalizar_firma_en_siga"))
					.equals(big1)) {
				siga = true;
			}
		}
		usuario.setExternalizarFirmaSIGA(siga);
		boolean loys = false;
		if (row.get("externalizar_firma_en_loys") != null) {
			if (((BigDecimal) row.get("externalizar_firma_en_loys"))
					.equals(big1)) {
				loys = true;
			}
		}
		usuario.setExternalizarFirmaLOYS(loys);
		boolean tyc = false;
		if (row.get("aceptacion_tyc") != null) {
			if (((BigDecimal) row.get("aceptacion_tyc")).equals(big1)) {
				tyc = true;
			}
		}
		usuario.setAceptacionTYC(tyc);
		boolean mr = false;
		if (row.get("IS_MULTIREPARTICION") != null) {
			if (((BigDecimal) row.get("IS_MULTIREPARTICION")).equals(big1)) {
				mr = true;
			}
		}
		usuario.setIsMultireparticion(mr);
		boolean cl = false;
		if (row.get("clave_publica") != null) {
			if (((BigDecimal) row.get("clave_publica")).equals(big1)) {
				cl = true;
			}
		}
		usuario.setClavePublica(cl);
		
		
		boolean usuActivo = false;
		if (row.get("usuario_activo") != null) {
			if (((BigDecimal) row.get("usuario_activo")).equals(big1)) {
				usuActivo = true;
			}
		}
		usuario.setUsuarioActivo(usuActivo);
		
		return usuario;
	}

	public Usuario obtenerUsuario(String username)
			throws SecurityAccesoDatosException {
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {

			StringBuffer consulta = new StringBuffer();

			consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
			consulta.append(TABLA_DATOS_USUARIO);
			consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
			consulta.append(FILTRO_WHERE);
			consulta.append(FILTRO_USERNAME);
			consulta.append(FILTRO_ADD_AND);
			consulta.append(FILTRO_ESTADO_REPA);

			Map<String, Object> row = getJdbcTemplate().queryForMap(
					consulta.toString(), new Object[] { username });
			return mapUsuarioBean(row);
		} catch (IncorrectResultSizeDataAccessException e) {
			// logger.error("Error al acceder a la base de datos: " +
			// e.getMessage(), e);
			return null;
		} catch (Exception e) {
			// logger.error("Error al acceder a la base de datos: " +
			// e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"Error al acceder a la base de datos:" + e.getMessage(), e);
		}
	}

	public List<Usuario> obtenerUsuariosPorSupervisor(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		StringBuffer consulta = new StringBuffer();

		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
		consulta.append(TABLA_DATOS_USUARIO);
		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
		consulta.append(FILTRO_WHERE);
		consulta.append(FILTRO_USERNAME_SUPERVISADO);

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				consulta.toString(), new Object[] { username });
		for (Map<String, Object> row : rows) {
			Usuario usuarioBean = mapUsuarioBean(row);
			//solo agrego los usuarios activo para ver como supervisados
			if(usuarioBean.getUsuarioActivo()!=null && usuarioBean.getUsuarioActivo())listUsuarios.add(usuarioBean);
		}

		return listUsuarios;
	}

	@Override
	public int obtenerCantidadDeReparticionHabilitadaPorNombre(String username)
			throws SecurityAccesoDatosException {

		int cantidadDeReparticionesEncontradas = 0;
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {
			cantidadDeReparticionesEncontradas = getJdbcTemplate().queryForInt(
					QUERY_TIENE_REPARTICIONES_HABILITADAS_USUARIO,
					new Object[] { username });
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}

		return cantidadDeReparticionesEncontradas;
	}
	
	
	public boolean reparticionSectorTieneUsuario(Integer sector, Integer reparticion)throws SecurityAccesoDatosException {
		int cantidad = 0;
		if (sector == null && reparticion ==null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {
			
			cantidad= getJdbcTemplate().queryForInt(
					QUERY_REPARTICION_SECTOR_TIENE_USUARIO,
					new Object[] { reparticion , sector});
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}
		return cantidad > 0;
	}

	@Override
	public List<String> obtenerReparticionesHabilitadasPorNombreUsuario(
			String username) throws SecurityAccesoDatosException {
		List<String> listRepas = new ArrayList<String>();
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		try {
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
					QUERY_REPARTICIONES_HABILITADAS_USUARIO,
					new Object[] { username });
			for (Map<String, Object> row : rows) {
				if (row.get("codigo_reparticion") != null) {
					listRepas.add((String) row.get("codigo_reparticion"));
				}
			}
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}

		return listRepas;
	}

	@Override
	public List<Usuario> obtenerUsuarios() throws SecurityAccesoDatosException {
		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		StringBuffer consulta = new StringBuffer();

		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
		consulta.append(TABLA_DATOS_USUARIO);
		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
		consulta.append(FILTRO_WHERE);
		consulta.append(FILTRO_ESTADO_REPA);
		
		//consulta.append(FILTRO_WHERE + FILTRO_SERGYV + FILTRO_ADD_AND + FILTRO_ESTADO_REPA);
		
		long time_start, time_end;
		
		time_start = System.currentTimeMillis();
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				consulta.toString());
		
		time_end = System.currentTimeMillis();
		
		this.logger.info("TIEMPO DE PROCESO AL REALIZAR QUERY FULLIMPORT: " + (time_end - time_start) +" ms");
		this.logger.info("TOTAL DE FILAS RECUPERADAS: "+ rows.size());
		
		for (Map<String, Object> row : rows) {
			try {
				Usuario usuarioBean = mapUsuarioBean(row);
				listUsuarios.add(usuarioBean);
			
			} catch (Exception e) {
				logger.info("Error al recueperar informacion del usuario: "+e.getMessage());
			}
		}

		return listUsuarios;
	}

	@Override
	public boolean licenciaActiva(String username, Date fecha)
			throws SecurityAccesoDatosException {
		int cantidad = 0;
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaQuery = dt.format(fecha);

		try {
			cantidad = getJdbcTemplate().queryForInt(
					QUERY_TIENE_LICENCIA,
					new Object[] { username, fechaQuery, fechaQuery,
							"terminado" });
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}

		return cantidad > 0;
	}

	@Override
	public Usuario obtenerUsuarioMigracion(String username)
			throws SecurityAccesoDatosException {

		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {

			StringBuffer consulta = new StringBuffer();

			consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
			consulta.append(TABLA_DATOS_USUARIO_REVISION);
			consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
			consulta.append(FILTRO_WHERE);
			consulta.append(FILTRO_USERNAME);
			consulta.append(FILTRO_ULTIMA_REVISION);
			

			Map<String, Object> row = getJdbcTemplate().queryForMap(
					consulta.toString(), new Object[] { username, username });
			return mapUsuarioBean(row);
		} catch (IncorrectResultSizeDataAccessException e) {
			// logger.error("Error al acceder a la base de datos: " +
			// e.getMessage(), e);
			return null;
		} catch (Exception e) {
			// logger.error("Error al acceder a la base de datos: " +
			// e.getMessage(), e);
			throw new SecurityAccesoDatosException(
					"Error al acceder a la base de datos:" + e.getMessage(), e);
		}
	}

	public List<Usuario> obtenerUsuariosPorRevisor(String username)
			throws SecurityAccesoDatosException {

		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		StringBuffer consulta = new StringBuffer();

		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
		consulta.append(TABLA_DATOS_USUARIO);
		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
		consulta.append(FILTRO_WHERE);
		consulta.append(FILTRO_USERNAME_REVISOR);

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				consulta.toString(), new Object[] { username });
		for (Map<String, Object> row : rows) {
			Usuario usuarioBean = mapUsuarioBean(row);
			listUsuarios.add(usuarioBean);
		}

		return listUsuarios;
	}

	public List<Usuario> obtenerUsuariosPorApoderado(String nombreUsuario)
			throws SecurityAccesoDatosException {

		if (nombreUsuario == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		StringBuffer consulta = new StringBuffer();

		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
		consulta.append(TABLA_DATOS_USUARIO);
		consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE2);
		consulta.append(FILTRO_WHERE);
		consulta.append(FILTRO_USERNAME_APODERADO);

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
				consulta.toString(), new Object[] { nombreUsuario });
		for (Map<String, Object> row : rows) {
			Usuario usuarioBean = mapUsuarioBean(row);
			listUsuarios.add(usuarioBean);
		}

		return listUsuarios;
	}

	@Override
	public List<String> obtenerReparticionesAdministradasPorNombreUsuario(
			String username) throws SecurityAccesoDatosException {
		List<String> listRepas = new ArrayList<String>();
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}

		try {
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(
					QUERY_REPARTICIONES_ADMINISTRADAS_USUARIO,
					new Object[] { username });
			for (Map<String, Object> row : rows) {
				if (row.get("codigo_reparticion") != null) {
					listRepas.add((String) row.get("codigo_reparticion"));
				}
			}
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}

		return listRepas;
	}
	
	@Override
	public int obtenerCantidadDeReparticionAdministradasPorNombre(String username)
			throws SecurityAccesoDatosException {

		int cantidadDeReparticionesEncontradas = 0;
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {
			cantidadDeReparticionesEncontradas = getJdbcTemplate().queryForInt(
					QUERY_TIENE_REPARTICIONES_ADMINISTRADAS_USUARIO,
					new Object[] { username });
		} catch (DataAccessException e) {
			throw new SecurityAccesoDatosException(
					"Ha ocurrido un error al consultar las reparticiones habilitadas del usuario",
					e);
		}

		return cantidadDeReparticionesEncontradas;
	}
	
	public Usuario obtenerUsuarioCompleto(String username)
			throws SecurityAccesoDatosException {
		if (username == null) {
			throw new IllegalArgumentException("Username no puede ser nulo");
		}
		try {

			StringBuffer consulta = new StringBuffer();

			consulta.append(QUERY_DATOSUSUARIO_REPARTICION_SECTOR_PARTE1);
			consulta.append(TABLA_DATOS_USUARIO);
			consulta.append(QUERY_DATOSUSUARIO__PARTE2_TODOS);
			consulta.append(FILTRO_WHERE);
			consulta.append(FILTRO_USERNAME);
			consulta.append(FILTRO_ADD_AND);
			consulta.append(FILTRO_ESTADO_REPA);

			Map<String, Object> row = getJdbcTemplate().queryForMap(
					consulta.toString(), new Object[] { username });
			
			return mapUsuarioBean(row);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new SecurityAccesoDatosException(
					"Error al acceder a la base de datos:" + e.getMessage(), e);
		}
	}
}
