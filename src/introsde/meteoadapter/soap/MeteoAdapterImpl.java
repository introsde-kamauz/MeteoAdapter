package introsde.meteoadapter.soap;
import introsde.document.model.*;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//Service Implementation

@WebService(endpointInterface = "introsde.meteoadapter.soap.MeteoAdapter", serviceName="MeteoService")
public class MeteoAdapterImpl implements MeteoAdapter {
	
	// es.
	// https://weatherplanner.azure-api.net/v1/Forecast/%7BLocation%7D/05/01/2018/11/20/2018?subscription-key=9a894cf7345245b1b6e8c6f2e7fea98c
	String WEATHER_API_KEY = "f825104a03624e6c96fb58f019caff42";
	String APIXU_API_KEY = "c2fc96f478124151913133824181701";
	
	WebTarget service;
	String URL = "https://weatherplanner.azure-api.net/v1/Forecast/";
	String URL2 = "http://api.apixu.com/v1/current.json?key=c2fc96f478124151913133824181701&q=";
	JsonNode node;
	
	@Override
	public Meteo getMeteo(String startdate, String city) {
		// TODO Auto-generated method stub
		try {
			String date = startdate.split("T")[0];
			String[] dateParts = date.split("-");
			String year = dateParts[0];
			String month = dateParts[1];
			String day = dateParts[2];
			
			URLEncoder encoder = null;
			
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Meteo.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			WebTarget webTarget = client.target(URL2+encoder.encode(city));
			Builder request = webTarget.request();
			
			Response response = request.get();
			Assert.assertTrue(response.getStatus() == 200);
			
			String json = response.readEntity(String.class);
			System.out.println(json);
			Meteo m = new Meteo();
			ObjectMapper mapper = new ObjectMapper();
			try {
				node = mapper.readTree(json);
				System.out.println(node.asText());
				if (node.findValue("text").isNull() || node.findValue("temp_c").isNull() || node.findValue("temp_f").isNull()) {
					m.setPrecipitation("not found");
					m.setMinTemperature(null);
					m.setMaxTemperature(null);
				}
				m.setPrecipitation(node.findValue("text").toString());
				m.setMinTemperature(node.findValue("temp_c").asDouble());
				m.setMaxTemperature(node.findValue("temp_f").asDouble());
				
			} catch (Exception er) {
				// TODO Auto-generated catch block
				m.setPrecipitation("not found");
				m.setMinTemperature(null);
				m.setMaxTemperature(null);
				er.printStackTrace();
			}
			
			return m;
			
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		return null;
	}
	
}