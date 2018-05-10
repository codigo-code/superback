package com.utn.dao;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public interface IConnection {
	public default Connection conexion() {
		Connection conn = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/parcial", "root", "root");

		} catch (Exception e) {
			System.out.println("No se pudo realizar la conexion a base de datos");
			e.printStackTrace();
		}

		return conn;
			
	}

}
