package com.utn.dao;

public interface IDao <T>{
	
	
	T Insert(T object);
	
	T DevolverDatos(T object);
	
	void imprimirFactura();
	
	void enviarInfoTC();
	
	void informarPago();
	
	void actualizarSaldo(T object);

}
