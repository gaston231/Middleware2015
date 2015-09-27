package org.springframework;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class envioJMSEndpoint {

	private final static Logger LOGGER = Logger.getLogger(envioJMSEndpoint.class.getName()); 

	public void enviarMensaje(ArrayList<String> mensaje) throws JMSException{
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("LealtadACME");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Se crean y envían los mensajes
        for (String msj : mensaje){
        	
        	msj = msj.replaceAll("\\r+|\\n+|\\t+|\\s+", "");
        	msj = msj.trim();
            Pattern p = Pattern.compile(".*<idCliente>(.*)</idCliente>.*<montoPagado>(.*)</montoPagado>", Pattern.MULTILINE);
            Matcher m = p.matcher(msj);
            String datos = "";
            while (m.find()) {
            	LOGGER.info("JMS-REG: 1:" + m.group(1).toString());
            	LOGGER.info("JMS-REG: 2:" + m.group(2).toString());
            	datos = datos + m.group(1).toString() + "-" + m.group(2).toString();
            }
            
        	
        	TextMessage message = session.createTextMessage(datos);
        	producer.send(message);
        	LOGGER.info("Envío mensaje: "+datos);
        }
        
        // Clean up
        session.close();
        connection.close();
		
		
		
	}
}
