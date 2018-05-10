package com.utn.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.factory.clienteFactory;
import com.utn.factory.tarjetaFactory;
import com.utn.modelo.cliente;
import com.utn.modelo.tarjeta;

/**
 * Servlet implementation class RegistroTarjeta
 */
@WebServlet("/RegistroTarjeta")
public class RegistroTarjeta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroTarjeta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("RegistroTarjeta.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		String cardholder=request.getParameter("cardholder");
		int edad=Integer.parseInt(request.getParameter("edad"));
		String marca=request.getParameter("marca");
		String vencimiento=request.getParameter("vencimiento");
		int consumo=Integer.parseInt(request.getParameter("consumo"));
		tarjeta t=new tarjeta(marca, cardholder, vencimiento);
		cliente c=new cliente(cardholder,edad,consumo);
		tarjetaFactory tf=new tarjetaFactory();
		clienteFactory cf=new clienteFactory();
		cf.Insert(c);
		tf.Insert(t);
		
	}
	
	public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }

	
}
