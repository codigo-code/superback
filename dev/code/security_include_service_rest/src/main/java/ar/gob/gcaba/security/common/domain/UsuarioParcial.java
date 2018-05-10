package ar.gob.gcaba.security.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

public class UsuarioParcial implements Comparable<UsuarioParcial> , Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3951196574539227325L;
	
	
	private String nombre;
	private String mail;
	private String uid;
	private String legajo;
	private String password;
	private List<String> permisos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	/**
	 * Clase que mapea un objeto Attributes a un objeto UsuarParcial
	 */
	public static class UsuarParcialMapper implements AttributesMapper {
		@Override
		public Object mapFromAttributes(Attributes att) throws NamingException {
			UsuarioParcial user = new UsuarioParcial();
			
			if(att.get("cn") != null){
				user.setUid((String) att.get("cn").get());
			} else {
				user.setUid("");
			}
			if(att.get("uid") != null){
				user.setNombre((String) att.get("uid").get());
			} else {
				user.setNombre("");
			}
			if(att.get("mail") != null){
				user.setMail((String) att.get("mail").get());
			} else {
				user.setMail("");
			}
			if(att.get("employeeNumber") != null){
				user.setLegajo((String) att.get("employeeNumber").get());
			} else {
				user.setLegajo("");
			}
			if(att.get("employeeType") != null){
				List<String> listaPermisos = new ArrayList<String>();
				for(int i =0;i < att.get("employeeType").size();i++){
					listaPermisos.add(att.get("employeeType").get(i).toString());
				}
				user.setPermisos(listaPermisos);
			} else {
				user.setPermisos(null);
			}

			return user;
		}
	}

	@Override
	public int compareTo(UsuarioParcial o) {
		return this.uid.compareTo(o.uid);
	}

}
