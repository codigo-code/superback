package com.utn.modelo;

public class TransactionDB implements ItransactionDB {

	@Override
	public void getTransaction() {

			System.out.println("traemos la trnsaaccion");
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delelteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public String deleteById(int id) {
		// TODO Auto-generated method stub
		return "Borramos con el id " + id;
	}

}
