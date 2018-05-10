package com.utn.factory;



import com.mysql.jdbc.PreparedStatement;
import com.utn.dao.IConnection;
import com.utn.dao.IDao;
import com.utn.modelo.cliente;

public class clienteFactory implements IDao<cliente>{

	
	private IConnection con;

	public clienteFactory() {
		this.con = new ConnectionFactory();
	}
	
	
	@Override
	public cliente Insert(cliente object) {
		String sql = "insert into cliente(cardholder,edad,consumo) values(?,?,?)";

		try {
			PreparedStatement pr = (PreparedStatement) con.conexion().prepareStatement(sql);
			pr.setString(1, object.getCardholder());
			pr.setInt(2,object.getEdad());
			pr.setInt(3,object.getConsumo());

			pr.execute();

		} catch (Exception e) {
			System.out.println("No se pudo cargar los datos del cliente en la base de datos");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return object;
	}

	

	@Override
	public cliente DevolverDatos(cliente object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void imprimirFactura() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarInfoTC() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarPago() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarSaldo(cliente object) {
		// TODO Auto-generated method stub
		
	}



}
