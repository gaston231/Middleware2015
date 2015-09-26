package org.springframework;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

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
import org.xml.sax.SAXException;



public class Request {
	



	public String obtenerRequest(String requestXML) throws Exception {

		
		try {
			
			String xsdPath = null; // path donde esta el archivo xsd
        	Source source = new StreamSource(new StringReader(requestXML)); 
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
			Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(source);
        } catch (IOException e) {
            
            throw new Exception(e.getMessage());
        }
		catch (SAXException e) 
		{
			throw new Exception(e.getMessage());
			
		}
		
		
		System.out.println(requestXML);
		
		return requestXML;
		
		
		
	
		
	}
}