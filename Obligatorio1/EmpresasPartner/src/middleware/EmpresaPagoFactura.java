package middleware;

import javax.jws.WebMethod;
import javax.jws.WebService;

import java.util.Date;

@WebService
public class EmpresaPagoFactura {
	@WebMethod
	public Long PagarFactura(Long idFactura, Short moneda, double monto, Date fechaHora){
    	return Long.MAX_VALUE;
    }
}
