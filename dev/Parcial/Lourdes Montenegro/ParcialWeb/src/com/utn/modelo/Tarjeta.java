package com.utn.modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Tarjeta {
	
	private String nomApellidoCliente;
	private Marca marca;
	private String numTarjeta;
	private String cardholder;
	private Date fechaVencimiento;
	private double saldo;
	
	public Tarjeta(String nomApellidoCliente, Marca marca, String numTarjeta,
			String cardholder, Date fechaVencimiento, double saldo) {
		super();
		this.nomApellidoCliente = nomApellidoCliente;
		this.marca = marca;
		this.numTarjeta = numTarjeta;
		this.cardholder = cardholder;
		this.fechaVencimiento = fechaVencimiento;
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Tarjeta [nomApellidoCliente=" + nomApellidoCliente + ", marca="
				+ marca + ", numTarjeta=" + numTarjeta + ", cardholder="
				+ cardholder + ", fechaVencimiento=" + fechaVencimiento
				+ ", saldo=" + saldo + "]";
	}
	public String getNomApellidoCliente() {
		return nomApellidoCliente;
	}
	public void setNomApellidoCliente(String nomApellidoCliente) {
		this.nomApellidoCliente = nomApellidoCliente;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
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
	public Tarjeta() {
		super();
	}
	
}
