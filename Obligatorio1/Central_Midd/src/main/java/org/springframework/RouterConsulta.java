package org.springframework;

import java.util.ArrayList;

import org.springframework.integration.annotation.Router;

public class RouterConsulta {
	
	@Router
	public String route(ArrayList<String> input) {
		System.out.println("LOG-ROUTER: Inicia");
		//Coloco en la salida correspondiente la operacion
		for (String temp : input) 			
		{
			if (temp.toLowerCase().contains("pagarfactura")) {
				System.out.println("LOG-ROUTER:" + temp);
	
				return "requestPartnerPago";
			}
			
			if (temp.toLowerCase().contains("ventaentradas")) {
				System.out.println("LOG-ROUTER:" + temp);
				return "requestPartnerVentaEntrada";
	
			}
			
			if (temp.toLowerCase().contains("pagooffline")) {
				System.out.println("LOG-ROUTER:" + temp);
				return "salidaOffLine";
	
			}
			return "Operacion No Soportada";
		}
		
		return null;
	}

}