package com.utn.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.utn.modelo.targeta;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletinformacion")
public class servletinformacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private conexionDAO con;
	private targeta targeta;

    public servletinformacion() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			targeta = con.informacion(Integer.parseInt(request.getParameter("numerotargeta")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter salida = response.getWriter();
		salida.println("<html><body>" + targeta.toString() + "</body></html>");
		
	}

}
