package com.utn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utn.dao.TarjetaDao;
import com.utn.modelo.Tarjeta;

/**
 * Servlet implementation class cobrarServlet
 */
@WebServlet("/cobrarServlet")
public class cobrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cobrarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateInString = request.getParameter("fechaVenc");
		Date fechaVenc;
		try {
			fechaVenc = formatter.parse(dateInString);
			Tarjeta t = new Tarjeta(request.getParameter("marca"), Integer.parseInt(request.getParameter("numero")), (java.sql.Date) fechaVenc);
			int monto= Integer.parseInt(request.getParameter("monto"));
			
			TarjetaDao td= new TarjetaDao();
			
			td.cobrarTarjeta(t, monto);
			
			td.mostrarDatos(t);
			
			
			
		} catch (ParseException e) {
			System.out.println("Problemas con el casteo de la fecha ");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	} 
}
