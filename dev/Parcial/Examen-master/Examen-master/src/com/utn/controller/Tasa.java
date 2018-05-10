package com.utn.controller;

import java.time.LocalDateTime;

public class Tasa {

	private double tasa;
	private String marca;
	
	public Tasa(String marca) {
		LocalDateTime fecha = LocalDateTime.now(); 
		
		if(marca.equals("squa"))
			this.tasa = fecha.getYear()/fecha.getMonthValue();
		
		else if(marca.equals("sco"))
			this.tasa = fecha.getDayOfMonth() * 0.5;
		
		else if(marca.equals("pere"))
			this.tasa = fecha.getMonthValue() * 0.1;
	}

	public double getTasa() {
		return tasa;
	}
	 
	
}
