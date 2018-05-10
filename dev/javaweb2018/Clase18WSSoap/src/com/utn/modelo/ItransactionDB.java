package com.utn.modelo;

public interface ItransactionDB {

	public void getTransaction();
	public void update(int id);
	
	public void delelteAll();
	
	public String deleteById(int id);
	
}
