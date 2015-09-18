package org.springframework.integration.samples.ws;


import org.springframework.integration.annotation.Router;

/**
 * @author Gary Russell
 * @since 2.0.2
 *
 */
public class RouterConsulta {
	
	@Router
	public String route(String input) {
		
		//Coloco en la salida correspondiente la operacion
		if (input.toLowerCase().contains("Pago Facturas")) {

			return "pagoFactura";
		}
		
		if (input.toLowerCase().contains("Venta entradas")) {
			
			return "ventaEntradas";

		}
		return "Operacion No Soportada";
	}

}
