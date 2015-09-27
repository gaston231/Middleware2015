package org.springframework;
import java.util.List;

import org.springframework.integration.annotation.Aggregator;

public class RespuestaAggregator {

	@Aggregator
	public String aggregate(List<String> respuestas) {
		StringBuilder sb = new StringBuilder();
		
		
		for (String respuesta : respuestas) {
			
			

			
			
		}
		
		
		
		
		
		
		// remove final comma, if any
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		if (sb.length() < 1) {
			return null;
		}
		return sb.toString();
	}
}