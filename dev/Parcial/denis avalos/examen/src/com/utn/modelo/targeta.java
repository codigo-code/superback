package com.utn.modelo;

import java.util.Date;

public class targeta {
	
	private String marca;
	private int numero;
	private String cardholder;
	private Date fechavencimiento;
	
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public Date getFechavencimiento() {
		return fechavencimiento;
	}
	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	
	public targeta(String marca, int numero, String cardholder, Date fechavencimiento) {
		super();
		this.marca = marca;
		this.numero = numero;
		this.cardholder = cardholder;
		this.fechavencimiento = fechavencimiento;
	}
	
	public targeta() {
	}
	
	@Override
	public String toString() {
		return "targeta	<br>marca=" + marca + ",	<br>numero=" + numero + ",	<br>cardholder=" + cardholder + ",	<br>fechavencimiento="
				+ fechavencimiento;
	}
	
	

}
