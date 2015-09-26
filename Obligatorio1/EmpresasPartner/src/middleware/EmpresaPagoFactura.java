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
		if (moneda != 1 && moneda !=2){
			Throwable t = new IllegalArgumentException("Argumento inválido");
            throw new FacturaNoValida("Moneda inválida" + moneda, t);
		}
		// Verifico id factura
		if (idFactura <= 0){
			Throwable t = new IllegalArgumentException("Argumento inválido");
            throw new FacturaNoValida("Factura inválida: " + idFactura, t);
		}	
		secuenciaFactura++;
		return secuenciaFactura.longValue();
    }
}
