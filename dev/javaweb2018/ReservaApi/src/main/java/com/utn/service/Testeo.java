package com.utn.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testeo {

	
	@GetMapping(value="/test.do")
	public String test() {
		return "testeando";
	}
	
}
