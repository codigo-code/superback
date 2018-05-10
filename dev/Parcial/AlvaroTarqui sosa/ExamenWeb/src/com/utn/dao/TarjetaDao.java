package com.utn.dao;

import com.utn.modelo.Tarjeta;

public class TarjetaDao {
	
	
	
	
	public void mostrarDatos(Tarjeta t) {
		CobrarDao cd = new CobrarDao();
		int posicion = cd.getTarjetaId(t);
		
		cd.mostrarDatosTarjeta(posicion);
		
	}
	
	
	public void cobrarTarjeta(Tarjeta t, int monto) throws ClassNotFoundException {
		CobrarDao cd = new CobrarDao();
		cd.sumarSaldo(t, monto);
	}
	

}
