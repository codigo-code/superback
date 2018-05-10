package com.utn.dao;

import com.mysql.jdbc.Connection;
import com.utn.modelo.Tarjeta;

public interface IDao {

	Connection getConexion();
	Tarjeta guardarTarjetaDatosTarjeta(Tarjeta tarjeta);
	Tarjeta buscarDatosTarjeta(int id);
	
}
