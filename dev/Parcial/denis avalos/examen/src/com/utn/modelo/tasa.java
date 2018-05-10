package com.utn.modelo;

import java.util.Date;

public class tasa {
	private double costo;
	private Date fecha;
	
	public double tasa(String marca,int importe) {
		costo=0;
		fecha=new Date();
		switch (marca) {
		case "SQUA":costo = (fecha.getYear()/fecha.getMonth())*importe;
			break;
			
		case "SCO":costo = (fecha.getDay()*0.5)*importe;
		break;
		
		case "PERE":costo = (fecha.getMonth()*0.1)*importe;
		break;
		
		default:costo=0;
			break;
		}
		return costo;
	}
	
	

}
