package com.utn.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnexMysql {

	
	private ConnexMysql() {
	}

	
	private static ConnexMysql conn = null;

	public static ConnexMysql newInstance() {

		if (conn == null)
			conn = new ConnexMysql();

		return conn;
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ExamenWeb", "root", "");

		return c;
	}

}