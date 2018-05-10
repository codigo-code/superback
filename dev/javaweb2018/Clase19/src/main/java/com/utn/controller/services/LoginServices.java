package com.utn.controller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.utn.model.User;
import com.utn.model.interfaces.IUserMethod;

@RestController
public class LoginServices {

	@Autowired
	private User user;
	
	@Autowired
	private IUserMethod userObj;
	
	@GetMapping(value="login.do")
	public String checkUser(@RequestParam("id") Integer id) {
		return new Gson().toJson(userObj.findById(id));
	}
	
	@GetMapping(value="inserto.do")
	public String inserto(@RequestParam("mail")String mail, @RequestParam("pass") String pass) {
	
		User u = new User();
		u.setId(3);
		u.setEmail(mail);
		u.setPassword(pass);
		userObj.save(u);
		return new Gson().toJson(u);
	}
	
}
