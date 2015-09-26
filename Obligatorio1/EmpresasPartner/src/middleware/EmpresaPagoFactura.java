package middleware;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

@WebService
@SchemaValidation
public class EmpresaPagoFactura{
	static Integer secuenciaFactura = 0;
	@WebMethod
	public Long PagarFactura(Long idFactura, Short moneda, double monto, Date fechaHora) throws FacturaNoValida, MonedaNoValida {
    	// Verifico moneda
		if (moneda != 0 && moneda !=1){
			Throwable t = new IllegalArgumentException("Argumento inv�lido");
            throw new FacturaNoValida("Moneda inv�lida" + moneda, t);
		}
		// Verifico id factura
		if (idFactura <= 0){
			Throwable t = new IllegalArgumentException("Argumento inv�lido");
            throw new FacturaNoValida("Factura inv�lida: " + idFactura, t);
		}	
		secuenciaFactura++;
		return secuenciaFactura.longValue();
    }
}
