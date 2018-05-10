package com.utn.fatory;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.utn.dao.IConnection;

public class ConnectionFactory implements IConnection {

	@Override
	public Connection getConnection() {

		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/utn", "root", "");

		} catch (Exception e) {
			System.out.println("No se pudo realizar la conexion");

		}
		return con;
	}

}
