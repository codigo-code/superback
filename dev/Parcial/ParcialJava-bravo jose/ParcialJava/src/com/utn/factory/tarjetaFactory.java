package com.utn.factory;



import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.utn.dao.IConnection;
import com.utn.dao.IDao;
import com.utn.modelo.tarjeta;

public class tarjetaFactory implements IDao<tarjeta> {

	private IConnection con;

	public tarjetaFactory() {
		this.con = new ConnectionFactory();
	}

	@Override
	public tarjeta DevolverDatos(tarjeta object) {
		tarjeta u=new tarjeta();
		
		String sql="select * from tarjeta where numtarjeta=?";
		
		try {
			PreparedStatement pr=(PreparedStatement) con.conexion().prepareStatement(sql);
			pr.setInt(1,object.getNumtarjeta());
			ResultSet rs= pr.executeQuery();
			while(rs.next()) {
				u.setNumtarjeta(rs.getInt(1));
				u.setMarca(rs.getString(2));
				u.setCardholder(rs.getString(3));
				u.setVencimiento(rs.getString(4));
				u.setTasa(rs.getInt(5));
				
			}
		
			} catch (Exception e) {
			System.out.println("No se pudo comprobar en la base de datos");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
			}
		return u;
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
	public void actualizarSaldo(tarjeta object) {
		// TODO Auto-generated method stub

	}

	@Override
	public tarjeta Insert(tarjeta object) {
		String sql = "insert into tarjeta(marca,cardholder,vencimiento) values(?,?,?)";

		try {
			PreparedStatement pr = (PreparedStatement) con.conexion().prepareStatement(sql);
			pr.setString(1, object.getMarca());
			pr.setString(2, object.getCardholder());
			pr.setString(3, object.getVencimiento());
			pr.execute();

		} catch (Exception e) {
			System.out.println("No se pudo cargar los datos de la tarjeta en la base de datos");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return object;
	}

}
