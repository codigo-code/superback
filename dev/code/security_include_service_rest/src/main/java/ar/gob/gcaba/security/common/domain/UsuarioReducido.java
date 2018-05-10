package ar.gob.gcaba.security.common.domain;

import java.io.Serializable;
import java.text.Normalizer;

public class UsuarioReducido implements Comparable<UsuarioReducido> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3141786539491083385L;

	protected String username;
	protected String nombreApellido;
	
	protected String codigoReparticion;
	

	public UsuarioReducido() {
	}
	
	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	/**
	 * Codigo de la repartición seleccioanda en el popup de multirepa
	 * @return String
	 */

	public String getCodigoReparticion() {
		return codigoReparticion;
	}

	public void setCodigoReparticion(String codigoReparticion) {
		this.codigoReparticion = codigoReparticion;
	}
	
	@Override
    public String toString() {
	return this.nombreApellido + " ( " + this.username + " - " + this.getCodigoReparticion() +" )";
    }
	
	public String getNombreApellidoPlano(){
		return   normalizeText(nombreApellido);
	}
	
	protected String normalizeText(String nombreApellido){
		
		nombreApellido = Normalizer.normalize(nombreApellido, Normalizer.Form.NFD);
		return nombreApellido.replaceAll("[^\\p{ASCII}]", "");
	}

	@Override
	public int compareTo(UsuarioReducido o) {		
		return this.username.compareTo(o.username);
	}

}
