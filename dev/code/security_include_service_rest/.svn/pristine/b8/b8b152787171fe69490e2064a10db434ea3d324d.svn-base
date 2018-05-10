package ar.gob.gcaba.security.common.ws.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.gob.gcaba.databaseConfiguration.propiedades.impl.DBAppPropertyImpl;


@Configuration
public class RestWSRedisClientEnumConfig {
	
	@Autowired
	private DBAppPropertyImpl dBProperty;
	
	@PostConstruct
	public void initRestClient(){
		RestClientRedisInformationEnum.initialize(dBProperty.getProperties());
	}
}
