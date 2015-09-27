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
		
	    
		int i= 0;
	    for (String respuesta : respuestas) {	    	 
//	    	i+=1;
//	    	if (i!=1)
//	    		//saco los demas tags de xml
//	    		respuesta = respuesta.replaceAll("<\\?xml.*?>", "");
	    	// Create the Message object
	    	sb.append(respuesta).append("\n");
			
		}
	    
	 // Create the Message object
//		Message<List<String>> message = MessageBuilder.withPayload(respuestas).build();
	
		// Send the Message to the handler's input channel
//		MessageChannel channel = channelResolver.resolveDestination("entradaLealtad");
		//channel.send(message);

	 
		return sb.toString();
	

	}
}