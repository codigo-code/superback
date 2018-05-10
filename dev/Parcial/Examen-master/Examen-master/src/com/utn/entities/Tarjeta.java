package com.utn.entities;

import java.util.Date;

public class Tarjeta {

	private String marca;
	private long numero;
	private String cardholder;
	private Date fechaVencimiento;
	private double consumo;
	private double tasa;
	
	
	public Tarjeta(String marca, long numero, String cardholder, Date fechaVencimiento, double consumo, double tasa) {
		super();
		this.marca = marca;
		this.numero = numero;
		this.cardholder = cardholder;
		this.fechaVencimiento = fechaVencimiento;
		this.consumo = consumo;
		this.tasa = tasa;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public String toString (){
		return "Marca "+marca+" numero de tarjeta "+numero+" Propietario: "+cardholder+" Fecha de Caducidad: "+fechaVencimiento+ 
				" Consumo "+consumo+ " Tasa: "+tasa;
    }

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getCardholder() {
		return cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
}
