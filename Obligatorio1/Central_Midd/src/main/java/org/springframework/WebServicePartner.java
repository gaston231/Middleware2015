package org.springframework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;

public class WebServicePartner {

	public void llamarPartner(String entrada) {
		
		
		ClassPathXmlApplicationContext context =
			new ClassPathXmlApplicationContext("/META-INF/integration/partnerConf.xml");
		DestinationResolver<MessageChannel> channelResolver = new BeanFactoryChannelResolver(context);

//		 Compose the XML message according to the server's schema
//		String requestXml =
//		"<ventaEntrada xmlns=\"http://middleware/\">" +
//				"<arg0>1</arg0>" +
//				"<arg1>1</arg1>" +
//				"<arg2>1</arg2>" +
//				"<arg3>1</arg3>" +
//		"</ventaEntrada>";
		
		// Compose the XML message according to the server's schema
		String requestXml =
		"<PagarFactura xmlns=\"http://middleware/\">" +
				"<arg0>1</arg0>" +
				"<arg1>1</arg1>" +
				"<arg2>1</arg2>" +
				"<arg3>1</arg3>" +
		"</PagarFactura>";
		
		// Create the Message object
		Message<String> message = MessageBuilder.withPayload(requestXml).build();

		// Send the Message to the handler's input channel
		MessageChannel channel = channelResolver.resolveDestination("canal1");
		channel.send(message);
	}

}
