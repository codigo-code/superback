package ar.gob.vuce.api;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.gob.vuce.config.BeanFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class TestApi {

	@Autowired
	private BeanFactory beanfactory;
	
	
	@Autowired
	@GetMapping(value="test.do")
	public String test(Gson gson) {
		return gson.toJson("{test:test}");
	}
	
	@GetMapping(value="token.do")
	public String getToken() {
		
		return beanfactory.getToken();
		
	}
	
	
}
