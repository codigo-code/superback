package com.utn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.utn.modelo.Tarjeta;

public class CobrarDao {
	
	public int getTarjetaId(Tarjeta t) {
		
		
		Connection conn = null;
		int id = 0;
		try {
			conn = ConnexMysql.newInstance().getConnection();
			String sql = "select* from tarjetas where numero='"+t.getNumero()+"' and marca='"+t.getMarca()+"'";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
			} 
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			System.out.println(e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	
	public void mostrarDatosTarjeta(int posicion) {
		
		
		Connection conn = null;
		try {
			conn = ConnexMysql.newInstance().getConnection();
			String sql = "select* from tarjetas where id='"+posicion;
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			

			ResultSet rs = ps.executeQuery();
			System.out.print(rs);

			
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			System.out.println(e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void sumarSaldo(Tarjeta t, int Monto) throws ClassNotFoundException {
		Connection conn = null;
		try {
			conn = ConnexMysql.newInstance().getConnection();
			PreparedStatement ps = (PreparedStatement) 
					conn.prepareStatement("select c.nombre and t.cliente='"+ t.getCliente()+"'");
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
