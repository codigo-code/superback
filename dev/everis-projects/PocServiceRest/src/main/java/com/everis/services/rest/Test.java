package com.everis.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.connectors.SOAPConnector;
import com.everis.webservice.afip.Autenticacion;
import com.everis.webservice.afip.ListaEmpresas;
import com.everis.webservice.afip.ListaEmpresasResponse;
import com.google.gson.Gson;

@RestController
public class Test {

	@Autowired
	Gson g;

	@GetMapping(value = "test.do")
	public String test(SOAPConnector soapConnector) {

		ListaEmpresas request = new ListaEmpresas();
		Autenticacion au = new Autenticacion();
		au.setCuit("20294779761");
		request.setAutentica(au);
		
		ListaEmpresasResponse response = (ListaEmpresasResponse) soapConnector
				.callWebService("https://testdia.afip.gob.ar/Dia/ws/wGesTabRef/wGesTabRef.asmx?wsdl", request);
		
		
		g.toJson(response);
		return g.toString();
	}
}
