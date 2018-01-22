package introsde.meteoadapter.soap;
import introsde.document.model.*;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.json.JSONObject;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface MeteoAdapter {

	// 1
	@WebMethod(operationName="getMeteo")
	@WebResult(name="getMeteo") 
	public Meteo getMeteo(String startdate, String city);

}