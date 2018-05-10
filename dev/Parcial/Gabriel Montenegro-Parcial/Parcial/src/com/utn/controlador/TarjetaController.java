package com.utn.controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.utn.dao.TarjetaDao;
import com.utn.modelo.Tarjeta;

public class TarjetaController {

	private TarjetaDao tarjetaDao;

	public boolean esValida(Tarjeta tarjeta) {
		tarjetaDao = new TarjetaDao();
		List<Tarjeta> tarjetas = tarjetaDao.obtenerTodas();
		boolean tarjetaExiste = false;
		for (Tarjeta tarjeta2 : tarjetas) {
			if(tarjeta2.getNumeroTarjeta() == tarjeta.getNumeroTarjeta()){
				tarjetaExiste = true;
			}
		}
		if (LocalDate.now().isAfter(tarjeta.getFechaVencimiento()) || !tarjetaExiste) {
			return false;
		}
		 
		
		return true;
	}

	public Tarjeta obtenerTarjeta(Long id) {

		tarjetaDao = new TarjetaDao();

		return tarjetaDao.select(id);
	}
}
