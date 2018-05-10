package com.utn.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.controller.TarjetaController;
import com.utn.controller.Tasa;
import com.utn.entities.Tarjeta;

@WebServlet(description = "Atiende directamente a index.jsp", urlPatterns = { "/InicioServlet" })
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public InicioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String marca = request.getParameter("marca");
		long numero = Long.parseLong(request.getParameter("numero"));
		String cardholder = request.getParameter("cardholder");
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha = request.getParameter("fechaVencimiento");
		Date fechaVencimiento = null;
			try { fechaVencimiento = (Date) formatoDelTexto.parse(strFecha);
			} catch (ParseException ex) {ex.printStackTrace();}
		
		double consumo = Double.parseDouble(request.getParameter("consumo"));
		
		Tasa tasa = new Tasa(marca);
		Tarjeta t = new Tarjeta(marca, numero, cardholder, fechaVencimiento, consumo, tasa.getTasa());
		
		System.out.println(t.toString());
		System.out.println(TarjetaController.validacion(t));
	
		RequestDispatcher dispatcher;
		
		if(TarjetaController.validacion(t)) {
			
			request.setAttribute("tarjeta", t);
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		
		}else {
			 dispatcher = request.getRequestDispatcher("/error_fecha_date.jsp");
			dispatcher.forward(request, response);
		}
		
		dispatcher.forward(request, response);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
