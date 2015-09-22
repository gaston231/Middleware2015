package org.springframework;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.channel.BeanFactoryChannelResolver;
import org.springframework.integration.xml.source.DomSourceFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.Node;



public class Request {
	



	public String obtenerRequest(String requestXML) throws Exception {

		
		System.out.println(requestXML);
		
		return requestXML;
		
		
		
		
		
//		issueResponseFor("ss");
//				ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
//				"/META-INF/integration/context.xml");
//		RequestGateway requestGateway = context.getBean("requestGateway", RequestGateway.class);
//		String reply = requestGateway.ResponseUno("ddddddd");
//		System.out.println(reply);
//		logger.info("\n\n++++++++++++ Replied with: " + reply + " ++++++++++++\n");
		
	}
}