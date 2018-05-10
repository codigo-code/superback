package ar.gob.gcaba.security.common.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario extends UsuarioReducido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2467508236383406312L;
	
	private String email;
//	private String codigoReparticion;
	private String nombreReparticion;
	private String codigoReparticionOriginal;
	private String nombreReparticionOriginal;
	private String nombre;
	private String apellido;
	private String supervisor;
	private String nombreApellidoSupervisor;
	private String cuit;
	private String codigoSectorInterno;
	private String nombreSectorInterno;
	private String codigoSectorInternoOriginal;
	private String nombreSectorInternoOriginal;
	private Boolean isMultireparticion;
	private String apoderado;
	private String nombreApellidoApoderado;
	private Boolean externalizarFirmaGEDO;
	private Boolean externalizarFirmaLOYS;
	private Boolean externalizarFirmaCCOO;
	private Boolean externalizarFirmaSIGA;
	private String cargo;
	private String ocupacion;
	private String cargoOriginal;
	private String usuarioRevisor;
	private String nombreApellidoUsuarioRevisor;
	private String secretario;
	private String nombreApellidoSecretario;
	private Boolean aceptacionTYC;
	private String sectorMesa;
	private String nombreSectorMesa;
    private Boolean notificarSolicitudPF;
    private String jurisdiccion;
	private String jurisdiccionSeleccionada;
	private String provincia;
	private String localidad;
	private String departamento;
	private Boolean clavePublica;
	private List<String> permisos;
	private Boolean validarPolitica;
	private Boolean usuarioActivo;
	//atributo de ldap
	@JsonIgnore
	protected Boolean isAccountNonExpired;
	@JsonIgnore
	protected Boolean isAccountNonLocked;
	@JsonIgnore
	protected Boolean isCredentialsNonExpired;
	@JsonIgnore
	protected Boolean isEnabled;
	private String password;
	@JsonIgnore
	private Collection<GrantedAuthority> authorities;
	
	public Usuario() {
		super();
	}
	
	public void setCodigoReparticionOriginal(String codigoReparticionOriginal) {
		this.codigoReparticionOriginal = codigoReparticionOriginal;
	}

	public String getCodigoReparticionOriginal() {
		return codigoReparticionOriginal;
	}

	public void setNombreReparticionOriginal(String nombreReparticionOriginal) {
		this.nombreReparticionOriginal = nombreReparticionOriginal;
	}

	public String getNombreReparticionOriginal() {
		return nombreReparticionOriginal;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	@Override
	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCuit() {
		return cuit;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    public String toString() {
	return this.nombreApellido + " ( " + this.username + " - " + this.getCodigoReparticion() +" )";
    }
    
	public boolean esUsuarioValido(){
		
		return (getNombreApellido() != null && getCuit() != null && getCodigoReparticion() != null) ? true : false;
	}
    
    @Override    
    public boolean equals (Object object){
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Usuario))return false;
        
        Usuario otherUsuario = (Usuario) object;
        
        if((this.getUsername()==null) ||  otherUsuario==null)
        	return false;
        
        return (this.getUsername().contentEquals(otherUsuario.getUsername()));              
    }

	public String getCodigoSectorInterno() {
		return codigoSectorInterno;
	}

	public void setCodigoSectorInterno(String codigoSectorInterno) {
		this.codigoSectorInterno = codigoSectorInterno;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public Boolean getIsMultireparticion() {
		return isMultireparticion;
	}

	public void setIsMultireparticion(Boolean isMultireparticion) {
		this.isMultireparticion = isMultireparticion;
	}

	public String getApoderado() {
		return apoderado;
	}

	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
	}

	public Boolean getExternalizarFirmaGEDO() {
		return externalizarFirmaGEDO;
	}

	public void setExternalizarFirmaGEDO(Boolean externalizarFirmaGEDO) {
		this.externalizarFirmaGEDO = externalizarFirmaGEDO;
	}

	public Boolean getExternalizarFirmaLOYS() {
		return externalizarFirmaLOYS;
	}

	public void setExternalizarFirmaLOYS(Boolean externalizarFirmaLOYS) {
		this.externalizarFirmaLOYS = externalizarFirmaLOYS;
	}

	public Boolean getExternalizarFirmaCCOO() {
		return externalizarFirmaCCOO;
	}

	public void setExternalizarFirmaCCOO(Boolean externalizarFirmaCCOO) {
		this.externalizarFirmaCCOO = externalizarFirmaCCOO;
	}

	public Boolean getExternalizarFirmaSIGA() {
		return externalizarFirmaSIGA;
	}

	public void setExternalizarFirmaSIGA(Boolean externalizarFirmaSIGA) {
		this.externalizarFirmaSIGA = externalizarFirmaSIGA;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getUsuarioRevisor() {
		return usuarioRevisor;
	}

	public void setUsuarioRevisor(String usuarioRevisor) {
		this.usuarioRevisor = usuarioRevisor;
	}

	public void setAceptacionTYC(Boolean aceptacionTYC) {
		this.aceptacionTYC = aceptacionTYC;
	}

	public Boolean getAceptacionTYC() {
		return aceptacionTYC;
	}

	public void setSectorMesa(String sectorMesa) {
		this.sectorMesa = sectorMesa;
	}

	public String getSectorMesa() {
		return sectorMesa;
	}	
	
	@JsonIgnore
	public String getGrupo() {
		return codigoReparticion + "-" + codigoSectorInterno;
	}
	
	@JsonIgnore
	public String getMesa() {
		return codigoReparticion + "-" + sectorMesa;
	}

	public String getCodigoSectorInternoOriginal() {
		return codigoSectorInternoOriginal;
	}

	public void setCodigoSectorInternoOriginal(String codigoSectorInternoOriginal) {
		this.codigoSectorInternoOriginal = codigoSectorInternoOriginal;
	}

	public void setNotificarSolicitudPF(Boolean notificarSolicitudPF) {
		this.notificarSolicitudPF = notificarSolicitudPF;
	}

	public Boolean getNotificarSolicitudPF() {
		return notificarSolicitudPF;
	}

	public String getJurisdiccion() {
		return jurisdiccion;
	}

	public void setJurisdiccion(String jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}

	public String getJurisdiccionSeleccionada() {
		return jurisdiccionSeleccionada;
	}

	public void setJurisdiccionSeleccionada(String jurisdiccionSeleccionada) {
		this.jurisdiccionSeleccionada = jurisdiccionSeleccionada;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * Nombre de la repartición seleccioanda en el popup de multirepa
	 * @return String
	 */
	public String getNombreReparticion() {
		return nombreReparticion;
	}
	
	public void setNombreReparticion(String nombreReparticionSeleccionada) {
		this.nombreReparticion = nombreReparticionSeleccionada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreApellidoSupervisor() {
		return nombreApellidoSupervisor;
	}

	public void setNombreApellidoSupervisor(String nombreApellidoSupervisor) {
		this.nombreApellidoSupervisor = nombreApellidoSupervisor;
	}


	public String getNombreSectorInternoOriginal() {
		return nombreSectorInternoOriginal;
	}

	public void setNombreSectorInternoOriginal(String nombreSectorInternoOriginal) {
		this.nombreSectorInternoOriginal = nombreSectorInternoOriginal;
	}

	public String getSecretario() {
		return secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

	public String getNombreApellidoSecretario() {
		return nombreApellidoSecretario;
	}

	public void setNombreApellidoSecretario(String nombreApellidoSecretario) {
		this.nombreApellidoSecretario = nombreApellidoSecretario;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getNombreSectorInterno() {
		return nombreSectorInterno;
	}

	public void setNombreSectorInterno(String nombreSectorInterno) {
		this.nombreSectorInterno = nombreSectorInterno;
	}

	public String getNombreApellidoApoderado() {
		return nombreApellidoApoderado;
	}

	public void setNombreApellidoApoderado(String nombreApellidoApoderado) {
		this.nombreApellidoApoderado = nombreApellidoApoderado;
	}

	public String getCargoOriginal() {
		return cargoOriginal;
	}

	public void setCargoOriginal(String cargoOriginal) {
		this.cargoOriginal = cargoOriginal;
	}

	public String getNombreApellidoUsuarioRevisor() {
		return nombreApellidoUsuarioRevisor;
	}

	public void setNombreApellidoUsuarioRevisor(String nombreApellidoUsuarioRevisor) {
		this.nombreApellidoUsuarioRevisor = nombreApellidoUsuarioRevisor;
	}

	public String getNombreSectorMesa() {
		return nombreSectorMesa;
	}

	public void setNombreSectorMesa(String nombreSectorMesa) {
		this.nombreSectorMesa = nombreSectorMesa;
	}

	public Boolean getClavePublica() {
		return clavePublica;
	}

	public void setClavePublica(Boolean clavePublica) {
		this.clavePublica = clavePublica;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}
	
	@JsonIgnore
	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}
	
	@JsonIgnore
	public Boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	
	@JsonIgnore
	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	
	@JsonIgnore
	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	
	@JsonIgnore
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	
	@JsonIgnore
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Boolean getValidarPolitica() {
		return validarPolitica;
	}

	public void setValidarPolitica(Boolean validarPolitica) {
		this.validarPolitica = validarPolitica;
	}

	public Boolean getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(Boolean usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}
	
}
