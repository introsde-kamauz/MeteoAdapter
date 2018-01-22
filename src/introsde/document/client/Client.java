package introsde.document.client;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import introsde.meteoadapter.soap.*;
import introsde.document.model.*;

public class Client {
    public static void main(String[] args) throws Exception {
    	
        URL url = new URL("http://10.38.224.39:6904/meteoadapter?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.meteoadapter.introsde/", "MeteoService");
        Service service = Service.create(url, qname);
        MeteoAdapter hello = service.getPort(MeteoAdapter.class);
        Meteo m = hello.getMeteo("2018-08-10","Rovereto");

        System.out.println("ARRIVA");
        System.out.println(m.getPrecipitation());
        System.out.println(m.getMaxTemperature());
        System.out.println(m.getMinTemperature());
    }
}