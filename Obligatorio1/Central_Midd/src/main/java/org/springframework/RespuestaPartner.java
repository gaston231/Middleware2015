package org.springframework;

import java.util.ArrayList;

import org.springframework.integration.annotation.Router;

public class RespuestaPartner {
	
	
	@Router
	public String routePartner(ArrayList<String> entradasRespuestas) {
		
		
		System.out.println("LOG-ROUTER: Inicia");
		//Coloco en la salida correspondiente la operacion
		for (String respuesta : entradasRespuestas) 			
		{
			if (respuesta.toLowerCase().contains("ok")) {
				System.out.println("LOG-ROUTER:" + respuesta);
	
				return "entradaLealtad";
			}
			
			if (respuesta.toLowerCase().contains("error")) {
				System.out.println("LOG-ROUTER:" + respuesta);
				return "requestPartnerVentaEntrada";
	
			}			
			
		}
		
		return null;
		
	
	}

}
