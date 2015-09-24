package org.springframework;

import java.util.ArrayList;

import org.springframework.integration.annotation.Router;

public class RouterConsulta {
	
	@Router
	public String route(ArrayList<String> input) {
		
		//Coloco en la salida correspondiente la operacion
		String element = null;
		for (String temp : input) 			
		{
			if (temp.toLowerCase().contains("PagarFactura")) {
	
				return "pagoFactura";
			}
			
			if (temp.toLowerCase().contains("ventaEntradas")) {
				
				return "ventaEntradas";
	
			}
			return "Operacion No Soportada";
		}
		
		return null;
	}

}