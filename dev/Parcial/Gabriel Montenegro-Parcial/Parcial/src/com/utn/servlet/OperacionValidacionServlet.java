package com.utn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.controlador.OperacionController;
import com.utn.modelo.Operacion;

public class OperacionValidacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OperacionController operacionController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.getRequestDispatcher("formularioOperacion.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Operacion operacion = new Operacion(Double.parseDouble(req
				.getParameter("consumo")));

		operacionController = new OperacionController();

		operacionController.esValida(operacion);

	}

}
