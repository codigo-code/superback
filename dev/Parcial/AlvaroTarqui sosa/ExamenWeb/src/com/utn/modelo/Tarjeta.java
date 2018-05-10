package com.utn.modelo;

import java.sql.Date;

public class Tarjeta {
	
	private String marca;
	private int numero;
	private Cliente cliente;
	private Date fechaVenc;
	
	
	public Tarjeta(String marca, int numero, Cliente cliente, Date fechaVenc) {
		super();
		this.marca = marca;
		this.numero = numero;
		this.cliente = cliente;
		this.fechaVenc = fechaVenc;
	}
	
	
	public Tarjeta(String marca, int numero, Date fechaVenc) {
		super();
		this.marca = marca;
		this.numero = numero;
		this.fechaVenc = fechaVenc;
	}
	
	
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFechaVenc() {
		return fechaVenc;
	}
	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}
	
	
	
	
	
}
