package com.utn.dao;

import java.util.List;

public interface ITransaction<T> {
	
	public T select(Long id);
	
	public List<T> obtenerTodas();


}
