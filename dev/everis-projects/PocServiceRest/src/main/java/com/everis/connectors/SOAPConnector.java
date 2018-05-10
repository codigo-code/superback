package com.everis.connectors;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

public class SOAPConnector extends WebServiceGatewaySupport {

	public Object callWebService(String url, Object request){
       
		try {
		 SaajSoapMessageFactory messageFactory;
			messageFactory = new SaajSoapMessageFactory(
			            MessageFactory.newInstance());
	        messageFactory.afterPropertiesSet();

	        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(
	                messageFactory);
	        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	        webServiceTemplate.setMarshaller(marshaller);
	        webServiceTemplate.setUnmarshaller(marshaller);
	        
		return webServiceTemplate.marshalSendAndReceive(url, request);
		
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
}
