package com.utn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.utn.model.respositories.ReservaBar;

@RestController
public class ReservaBarApi {

	@Autowired
	private ReservaBar reservaBar;
	
	@Autowired
	private Gson gson;
	
	@GetMapping(value="getBar")
	public String getAllBar() {
		
	 return gson.toJson(reservaBar.findAll());
	}
	
	
	@CrossOrigin
	@GetMapping(value="getBar/{id}")
	public ResponseEntity getBarById(@PathVariable("id") Integer id) {
		
	 return  ResponseEntity.ok(gson.toJson(reservaBar.findById(id)));
	}
}
