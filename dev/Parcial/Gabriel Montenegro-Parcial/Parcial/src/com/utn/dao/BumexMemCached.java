package com.utn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.utn.modelo.Tarjeta;

public class BumexMemCached {

	private static BumexMemCached bumex;

	private List<Tarjeta> lista;

	public static BumexMemCached newInstance() {
		if (bumex == null) {
			bumex = new BumexMemCached();
			return bumex;
		}
		return bumex;
	}

	private BumexMemCached() {
		lista = new ArrayList<>();
	}
	
	public List<Tarjeta>obtenerTodas(){
		
		return this.lista;
	}

	public Tarjeta get(Long id) {

		for (Tarjeta tarjeta : this.lista) {
			if (tarjeta.getId() == id) {
				return tarjeta;
			}
		}

		return null;
	}
	
}
