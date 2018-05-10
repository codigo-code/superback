package com.utn.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utn.modelo.validar;

@WebServlet("/servletvalidaroperacion")
public class servletvalidaroperacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private validar validar;
	
	
    public servletvalidaroperacion() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validar = new validar();
		PrintWriter salida = response.getWriter();
		if(validar.validaroperacion(Integer.parseInt(request.getParameter("monto")))) {
			salida.println("<html><body><h1>la operacion es valida</h1></body></html>");
		}
		
	}

}
