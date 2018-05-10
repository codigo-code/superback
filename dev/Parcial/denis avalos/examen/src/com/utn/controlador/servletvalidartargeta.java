package com.utn.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.modelo.validar;
import com.utn.modelo.targeta;

@WebServlet("/servletvalidartargeta")
public class servletvalidartargeta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private validar validar;
	private conexionDAO con;
    private targeta targeta;   
    public servletvalidartargeta() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validar = new validar();
		try {
			targeta = con.informacion(Integer.parseInt(request.getParameter("numerotargeta")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter salida = response.getWriter();
		if(validar.validartargeta(targeta)) {
			salida.println("<html><body><h1>la operacion es valida</h1></body></html>");
		}
		
		
	}

}
