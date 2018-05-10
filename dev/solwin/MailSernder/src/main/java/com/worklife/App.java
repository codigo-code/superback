package com.worklife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

import com.worklife.model.MailMail;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	
	
	private static ConfigurableApplicationContext context; 
	
	public static void main(String[] args) {
		context =  SpringApplication.run(App.class, args);
		
		
		
	}

	@GetMapping(value="envio.do")
	public String go() {
		
//		MailMail mm = (MailMail) context.getBean("mailMail");
		return "ok";
	}
	

}
