package com.utn.modelo;

import java.util.Date;

public class validar {
	
	private int maxmonto=100;
	private Date fecha;
	private boolean valido;
	
	public boolean validartargeta(targeta targeta) {
		fecha = new Date();
		valido=false;
		if(fecha.getYear()>=targeta.getFechavencimiento().getYear()) {
			if(fecha.getMonth()>=targeta.getFechavencimiento().getMonth()) {
				if(fecha.getDay()>=targeta.getFechavencimiento().getDay()) {
					valido = true;
				}
			}
		}
		
		return valido;
	}

	public boolean validaroperacion(int monto) {
		valido=false;
		if (monto<=maxmonto) {
			valido = true;
		}
		return valido;
	}
	
}