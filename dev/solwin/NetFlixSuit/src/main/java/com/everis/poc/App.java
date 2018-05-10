package com.everis.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class App 
{
	 @RequestMapping("/")
	  public String home() {
	    return "Hello World";
	  }

	  public static void main(String[] args) {
	    SpringApplication.run(App.class, args);
	  }

}
