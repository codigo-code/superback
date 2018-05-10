package com.utn.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.controlador.TarjetaController;
import com.utn.modelo.MarcaTarjeta;
import com.utn.modelo.Tarjeta;

public class TarjetaValidacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter
			.ofPattern("yyyy-MM-dd");
	private TarjetaController tarjetaController;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		try {
			Tarjeta tarjeta = new Tarjeta(MarcaTarjeta.valueOf(req
					.getParameter("marca")), LocalDate.parse(
					req.getParameter("fechaVencimiento"), FORMATO_FECHA),
					Integer.parseInt(req.getParameter("numeroTarjeta")),
					req.getParameter("cardholder"));
			tarjetaController = new TarjetaController();
			if(!tarjetaController.esValida(tarjeta)){
				throw new IllegalArgumentException();
			}
			req.getRequestDispatcher("mensajeExito").forward(req, resp);;
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			dispatcher = req.getRequestDispatcher("formularioTarjeta.jsp");
			req.setAttribute("mensajeError", "La tarjeta no existe o no es valida");
			dispatcher.forward(req, resp);
		}

	}
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("formularioTarjeta.jsp").forward(req, resp);

	}
}
