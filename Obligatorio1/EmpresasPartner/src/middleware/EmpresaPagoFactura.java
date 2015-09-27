package middleware;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

@WebService
@SchemaValidation
public class EmpresaPagoFactura{
	//static Integer secuenciaFactura = 0;
	@WebMethod
	public ResultadoPago PagarFactura(Long idCliente, Long idFactura, Short moneda, double monto, Date fechaHora) throws FacturaNoValida, MonedaNoValida {
    	ResultadoPago resultado = new ResultadoPago();
    	boolean huboError = false;
		
    	// Verifico moneda
		if (moneda != 1 && moneda !=2){
			//Throwable t = new IllegalArgumentException("Argumento inv�lido");
            //throw new FacturaNoValida("Moneda inv�lida" + moneda, t);
			resultado.codigoResultado = -1;
			resultado.mensajeResultado = "Error: Moneda inv�lida: " + moneda;
			huboError = true;
		}
		
		// Verifico id factura
		if (idFactura <= 0){
			//Throwable t = new IllegalArgumentException("Argumento inv�lido");
            //throw new FacturaNoValida("Factura inv�lida: " + idFactura, t);
			resultado.codigoResultado = -2;
			resultado.mensajeResultado = "Error: Factura inv�lida: " + idFactura;
			huboError = true;
		}	
		
		// Termino de armar el resultado dependiendo del exito o no de la operacion
		if (huboError){
			Integer resultadoError = -1;
			resultado.idCobro = resultadoError.longValue();
		} else {
			//secuenciaFactura++;
			//resultado.idCobro = secuenciaFactura.longValue();
			resultado.codigoResultado = 0;
			resultado.mensajeResultado = "OK";
		}
		resultado.idCliente = idCliente;
	    // Id de cobro de salida es el mismo que el id de factura de entrada
		resultado.idCobro = idFactura;
		resultado.montoPagado = monto;
		return resultado;
    }
}
