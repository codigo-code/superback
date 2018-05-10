package com.utn.modelo;


public class tarjeta {
	private int numtarjeta;
	private String marca;
	private String cardholder;
	private String vencimiento;
	private int tasa;
	public int getNumtarjeta() {
		return numtarjeta;
	}
	public void setNumtarjeta(int numtarjeta) {
		this.numtarjeta = numtarjeta;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public int getTasa() {
		return tasa;
	}
	public void setTasa(int tasa) {
		this.tasa = tasa;
	}
	public tarjeta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tarjeta(int numtarjeta, String marca, String cardholder, String vencimiento, int tasa) {
		super();
		this.numtarjeta = numtarjeta;
		this.marca = marca;
		this.cardholder = cardholder;
		this.vencimiento = vencimiento;
		this.tasa = tasa;
	}
	public tarjeta(String marca, String cardholder, String vencimiento, int tasa) {
		super();
		this.marca = marca;
		this.cardholder = cardholder;
		this.vencimiento = vencimiento;
		this.tasa = tasa;
	}
	public tarjeta(String marca, String cardholder, String vencimiento) {
		super();
		this.marca = marca;
		this.cardholder = cardholder;
		this.vencimiento = vencimiento;
	}
	
	
	
}