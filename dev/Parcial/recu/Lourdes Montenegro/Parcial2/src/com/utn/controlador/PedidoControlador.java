package com.utn.controlador;

import com.utn.fatory.PedidoFactory;
import com.utn.modelo.Pedido;

public class PedidoControlador {
	private PedidoFactory pf;
	public PedidoControlador() {
	pf=new PedidoFactory();
	}
	
	public void guardarPedido(Pedido pedido){
	
	pf.guardarPedido(pedido);
	
	
	}
	}

