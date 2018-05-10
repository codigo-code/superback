package com.utn.fatory;

import com.mysql.jdbc.PreparedStatement;
import com.utn.dao.IConnection;
import com.utn.dao.ITransaction;
import com.utn.modelo.Pedido;

public class PedidoFactory implements ITransaction{

	private IConnection conex;
	
	public PedidoFactory() {
	conex=new ConnectionFactory();
	
	}
	@Override
	public Pedido guardarPedido(Pedido pedido) {
		String sql="insert into pedidos(puesto,combo,precio)values(?,?,?)";
		try {
			
		PreparedStatement pr=(PreparedStatement) conex.getConnection().prepareStatement(sql);
		
		pr.setString(1, pedido.getPuesto());
		pr.setString(2, pedido.getCombo());
		pr.setInt(3,pedido.getPrecio());
		
		pr.execute();
		
		} catch (Exception e) {
			System.out.println("No se cargaron los datos");
		}
		return pedido;
	}

}
