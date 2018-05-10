package com.utn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import javax.persistence.Table;

@Entity
@Table
@Component
public class Remis {


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public String getNombreChofer() {
		return nombreChofer;
	}
	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String destino;
	@Column
	private String origen;
	@Column
	private double precio;
	@Column
	private double distancia;
	@Column
	private String nombreChofer;
	
	
}
