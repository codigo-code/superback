package com.utn.dao;

import java.util.List;

import com.utn.modelo.Tarjeta;

public class TarjetaDao implements ITransaction<Tarjeta> {

	public Tarjeta select(Long id) {

		return BumexMemCached.newInstance().get(id);
	}

	@Override
	public List<Tarjeta> obtenerTodas() {


		return BumexMemCached.newInstance().obtenerTodas();
	}

}
