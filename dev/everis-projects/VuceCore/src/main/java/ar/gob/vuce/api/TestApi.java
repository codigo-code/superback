package ar.gob.vuce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class TestApi {

	
	@Autowired
	@RequestMapping(value="test.do")
	public String test(Gson gson) {
		return gson.toJson("{test:test}");
	}
}
