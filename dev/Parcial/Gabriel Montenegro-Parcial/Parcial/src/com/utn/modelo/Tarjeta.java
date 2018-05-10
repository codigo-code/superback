package com.utn.modelo;

import java.time.LocalDate;

public class Tarjeta {

	private Long id;
	private MarcaTarjeta marca;
	private LocalDate fechaVencimiento;
	private int numeroTarjeta;
	private String cardHolder;

	public Tarjeta() {
		// TODO Auto-generated constructor stub
	}

	public Tarjeta(MarcaTarjeta marca, LocalDate fechaVencimiento,
			int numeroTarjeta, String cardHolder) {
		this.marca = marca;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroTarjeta = numeroTarjeta;
		this.cardHolder = cardHolder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MarcaTarjeta getMarca() {
		return marca;
	}

	public void setMarca(MarcaTarjeta marca) {
		this.marca = marca;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

}
