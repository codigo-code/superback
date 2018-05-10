package com.utn.modelo;

public class cliente {
	private int id;
	private String cardholder;
	private int edad;
	private int consumo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getConsumo() {
		return consumo;
	}
	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}
	public cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public cliente(String cardholder, int edad, int consumo) {
		super();
		this.cardholder = cardholder;
		this.edad = edad;
		this.consumo = consumo;
	}
	public cliente(int id, String cardholder, int edad, int consumo) {
		super();
		this.id = id;
		this.cardholder = cardholder;
		this.edad = edad;
		this.consumo = consumo;
	}
	
	
}
