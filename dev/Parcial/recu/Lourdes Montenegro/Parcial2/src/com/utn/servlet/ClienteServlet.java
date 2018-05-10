package com.utn.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.controlador.PedidoControlador;
import com.utn.modelo.Pedido;

public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
PedidoControlador pc=new PedidoControlador ();
		
		RequestDispatcher rd=request.getRequestDispatcher("factura.jsp");
		
		if (request.getParameter("pedido").equals("pumper feliz") ) {
			int precio = 1000;
			Pedido pe = new Pedido(request.getParameter("puesto"),
					request.getParameter("pedido"), precio);
			pc.guardarPedido(pe);
			
			request.setAttribute("puesto", pe.getPuesto());
			request.setAttribute("pedido", pe.getCombo());
			request.setAttribute("precio", pe.getPrecio());
			rd.forward(request, response);
			
		} else if (request.getParameter("pedido").equals("pumper mediano")) {
			int precio = 2000;
			Pedido pe = new Pedido(request.getParameter("puesto"),
					request.getParameter("pedido"), precio);
			pc.guardarPedido(pe);
			
			request.setAttribute("puesto", pe.getPuesto());
			request.setAttribute("pedido", pe.getCombo());
			request.setAttribute("precio", pe.getPrecio());
			rd.forward(request, response);
		
		} else if (request.getParameter("pedido").equals("pumper grande")) {
			int precio = 4000;
			Pedido pe = new Pedido(request.getParameter("puesto"),
					request.getParameter("pedido"), precio);
			pc.guardarPedido(pe);
			
			request.setAttribute("puesto", pe.getPuesto());
			request.setAttribute("pedido", pe.getCombo());
			request.setAttribute("precio", pe.getPrecio());
			rd.forward(request, response);
		
		} else if (request.getParameter("pedido").equals("super pumper")) {
			int precio = 5000;
			Pedido pe = new Pedido(request.getParameter("puesto"),
					request.getParameter("pedido"), precio);
			pc.guardarPedido(pe);
		
			request.setAttribute("puesto", pe.getPuesto());
			request.setAttribute("pedido", pe.getCombo());
			request.setAttribute("precio", pe.getPrecio());
			rd.forward(request, response);
		
		}
	}

}
