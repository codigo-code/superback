package com.utn.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utn.modelo.targeta;

public class conexionDAO {
	private Connection c ;
	private String sql;
	private PreparedStatement ps;
	private targeta targeta;
	
	
	public targeta informacion(int numerotargeta) throws SQLException {
		sql="select * from targetas where ntargeta=" + numerotargeta;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:8080/targetas", "root", "");
			ps =  c.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			
		}
		return targeta;
	}
	

}
