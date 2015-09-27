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
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
	    XPath xpath = xpathFactory.newXPath();
	    System.out.println("MSG " + "va a entrar al agregator");
		ArrayList<String> colaJMS = new ArrayList<String>();
	    
	    for (String respuesta : respuestas) {
	    	
	    	
	    	colaJMS.add(respuesta);
	    	// Create the Message object
	    	sb.append(respuesta).append("\n");
//			sb.append(respuestas).append("XXXX");		
//			InputSource source1 = new InputSource(new StringReader(respuesta));
//			String msg = xpath.evaluate("/return/", source1);		
//			System.out.println("MSG " + msg);

//			sb.append(msg).append(",");		
		}
		
	 // Create the Message object
		Message<ArrayList<String>> message = MessageBuilder.withPayload(colaJMS).build();

		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveDestination("entradaLealtad");
		channel.send(message);
		
		
		
		System.out.println("termino");
		return sb.toString();
	

	}
}