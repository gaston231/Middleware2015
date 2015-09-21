package middleware;

import javax.xml.ws.WebFault;

@WebFault
public class MonedaNoValida extends Exception {
    public MonedaNoValida(String message, Throwable cause) {
        super(message, cause);
    }
}
