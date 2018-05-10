package ar.gob.vuce.config;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class BeanFactory {

	@Bean
	public String getToken() {
		String tok;
		try {
			tok = Jwts.builder().setSubject("user/Charles")
					     .setExpiration(new Date(1300819380)
					     ).claim("name", "Charly")
					     .claim("scope", "admin")
					     .signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tok="";
		}
		return tok;

	}
}
