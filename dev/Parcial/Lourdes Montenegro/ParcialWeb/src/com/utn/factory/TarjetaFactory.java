package com.utn.factory;

import java.sql.DriverManager;
import java.time.LocalDate;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.utn.dao.IDao;
import com.utn.modelo.Marca;
import com.utn.modelo.Tarjeta;

public class TarjetaFactory implements IDao{

    private	Connection con;
	@Override
	public Connection getConexion() {
		
		try {
		con= (Connection) DriverManager.getConnection("jdbc:mysql//localhost/utn","root","");	
			
		} catch (Exception e) {
			System.out.println("No se realizo la coneccion a la base de datos");
		}
		return con;
	}

	@Override
	public Tarjeta guardarTarjetaDatosTarjeta(Tarjeta tarjeta) {
		String sql="insert into tarjetas(nombre_pellido_cliente,marca,numero_tarjeta,cardholder,fecha_vencimiento,saldo)values(?,?,?,?,?,?)";
		try {
			PreparedStatement pr=(PreparedStatement) con.prepareStatement(sql);
			
			pr.setString(1, tarjeta.getNomApellidoCliente());
			pr.setString(2, tarjeta.getMarca().toString());
			pr.setString(3, tarjeta.getNumTarjeta());
			pr.setString(4, tarjeta.getCardholder());
			pr.setDate(5,tarjeta.getFechaVencimiento());
			pr.setDouble(6, tarjeta.getSaldo());
		
		
		
		} catch (Exception e) {
			System.out.println("No se realizo la carga");
		}
		return tarjeta;
	}

	@Override
	public Tarjeta buscarDatosTarjeta(int id) {
		// TODO Auto-generated method stub
		return null;

	}
}
