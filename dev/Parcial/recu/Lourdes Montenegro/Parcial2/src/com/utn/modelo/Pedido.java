package com.utn.modelo;

public class Pedido {

	private String puesto;
	private String combo;
	private int precio;

	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getCombo() {
		return combo;
	}
	public void setCombo(String combo) {
		this.combo = combo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}
	public Pedido(String puesto, String combo, int precio) {
		super();
		this.puesto = puesto;
		this.combo = combo;
		this.precio = precio;
	}
	
	
}
