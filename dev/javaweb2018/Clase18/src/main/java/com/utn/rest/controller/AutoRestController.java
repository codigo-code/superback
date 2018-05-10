package com.utn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.utn.model.Auto;

@RestController
public class AutoRestController {

	@Autowired
	private Auto auto;

	@Autowired
	Gson gson;
	
	@GetMapping(value="auto.do")
	public String getAutoDestalles() {

		auto.setMarca("toyota");
		auto.setModelo("corola");
		auto.setColor("rojo");
		//transforma de un objeto a Json
		return gson.toJson(auto);
	}
}
