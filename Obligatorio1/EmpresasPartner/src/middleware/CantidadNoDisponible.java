package middleware;

import javax.xml.ws.WebFault;

@WebFault
public class CantidadNoDisponible extends Exception{
    public CantidadNoDisponible(String message) {
        super(message);
    }
}
