package com.utn.modelo;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String saldoTarjeta;
	private Tarjeta tarjeta;
	
	
	public Cliente(String nombre, String apellido, String dni, String saldoTarjeta, Tarjeta tarjeta) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.saldoTarjeta = saldoTarjeta;
		this.tarjeta = tarjeta;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getSaldoTarjeta() {
		return saldoTarjeta;
	}
	public void setSaldoTarjeta(String saldoTarjeta) {
		this.saldoTarjeta = saldoTarjeta;
	}
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
		

	
		
}
