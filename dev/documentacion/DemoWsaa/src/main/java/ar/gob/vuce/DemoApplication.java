package ar.gob.vuce;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

import org.apache.axis.AxisProperties;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class DemoApplication {

	public static void main(String [] args ) {

		String LoginTicketResponse = null;
	
		System.setProperty("http.proxyHost", "");
		System.setProperty("http.proxyPort", "80");
		
		System.out.println();
		System.out.println();
				
		// Read config from phile
		Properties config = new Properties();
		
	
		try {
			config.load(new FileInputStream("./wsaa_client.properties"));
		} catch (Exception e) {
			//e.printStackTrace();
		} 
		
		String endpoint = config.getProperty("endpoint","https://wsaahomo.afip.gov.ar/ws/services/LoginCms"); 
		String service = config.getProperty("service","wconsdeclaracion");
		String dstDN = config.getProperty("dstdn","cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239");
		
		String p12file = config.getProperty("keystore","c:/tmp/certificadoVuce.pfx");
		String signer = config.getProperty("keystore-signer","1");
		String p12pass = config.getProperty("keystore-password","vuce");
		
		// Set proxy system vars
		System.setProperty("http.proxyHost", config.getProperty("http_proxy",""));
		System.setProperty("http.proxyPort", config.getProperty("http_proxy_port",""));
		System.setProperty("http.proxyUser", config.getProperty("http_proxy_user",""));
		System.setProperty("http.proxyPassword", config.getProperty("http_proxy_password",""));
		
		// Set the keystore used by SSL
		System.setProperty("javax.net.ssl.trustStore", config.getProperty("trustStore","c:/tmp/wsaahomo.jks"));
		System.setProperty("javax.net.ssl.trustStorePassword",config.getProperty("trustStore_password","")); 
		
		
		AxisProperties.setProperty("axis.socketSecureFactory","org.apache.axis.components.net.SunFakeTrustSocketFactory");
		
		Long TicketTime = new Long(config.getProperty("TicketTime","36000"));
	
		// Create LoginTicketRequest_xml_cms
		byte [] LoginTicketRequest_xml_cms = afip_wsaa_client.create_cms(p12file, p12pass, 
					signer, dstDN, service, TicketTime);
			
		// Invoke AFIP wsaa and get LoginTicketResponse
		try {
			LoginTicketResponse = afip_wsaa_client.invoke_wsaa ( LoginTicketRequest_xml_cms, endpoint );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get token & sign from LoginTicketResponse
		try {
			Reader tokenReader = new StringReader(LoginTicketResponse);
			Document tokenDoc = new SAXReader(false).read(tokenReader);
			
			String token = tokenDoc.valueOf("/loginTicketResponse/credentials/token");
			String sign = tokenDoc.valueOf("/loginTicketResponse/credentials/sign");
			
			System.out.println("TOKEN: " + token);
			System.out.println("SIGN: " + sign);
		} catch (Exception e) {
			System.out.println(e);
		}	

		System.out.println();
		System.out.println();		

	}
}
