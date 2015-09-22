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
        	TextMessage message = session.createTextMessage(msj);
        	producer.send(message);
        	LOGGER.info("Envío mensaje: "+msj);
        }
        
        // Clean up
        session.close();
        connection.close();
		
		
		
	}
}
