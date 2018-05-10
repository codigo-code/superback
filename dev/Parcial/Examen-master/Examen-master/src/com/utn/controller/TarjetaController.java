package com.utn.controller;

import java.time.LocalDate;
import java.util.Date;

import com.utn.entities.Tarjeta;

public class TarjetaController {

	public static boolean validacion(Tarjeta t) {
		boolean ok= false;
		Date fecha = new Date();
		
		int v= fecha.compareTo(t.getFechaVencimiento());
		
		if(v < 0) 
			ok = validacionConsumo(t);
		
		return ok;
	}

	private static boolean validacionConsumo(Tarjeta t) {
		boolean ok= false;
		
		if(t.getConsumo() <= 100)
			ok=true;
			
		return ok;
	}
}
