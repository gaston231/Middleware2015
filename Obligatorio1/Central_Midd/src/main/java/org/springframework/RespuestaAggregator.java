package org.springframework;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.xml.sax.InputSource;

public class RespuestaAggregator {

	@Aggregator
	public String aggregate(List<String> respuestas) throws XPathExpressionException {
		
		//envio la copia a el canal de la cola
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("/META-INF/integration/context.xml");
			DestinationResolver<MessageChannel> channelResolver = new BeanFactoryChannelResolver(context);
				
		
		StringBuilder sb = new StringBuilder();   
		
		List<String> lealtad = new ArrayList<String>();
		int i= 0;
	    for (String respuesta : respuestas) {	    	 
	    	
	    	if (!respuesta.toLowerCase().contains("error"))
	    		lealtad.add(respuesta);
	    	//Añado siempre a la ventanilla
	    	sb.append(respuesta).append("\n");
			
		}
	    
	    //ENVIO A LA COLA DE LEALTAD
	 // Create the Message object
		Message<List<String>> message = MessageBuilder.withPayload(lealtad).build();
	
		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveDestination("entradaLealtad");
		channel.send(message);
		
		
		//RETORNO A LA VENTANILLA
	 
		return sb.toString();
	

	}
}