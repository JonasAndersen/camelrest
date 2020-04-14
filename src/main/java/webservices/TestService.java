package webservices;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class TestService {

    @WebMethod
    public String sayHello(String msg){
        return "Hello "+msg;
    }

    public static void main(String[] args){
        Endpoint.publish("http://localhost:8080/testWS", new TestService());
    }
}
