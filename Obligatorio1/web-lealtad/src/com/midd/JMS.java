package com.midd;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.mysql.MySQLConsultas;

public class JMS {

	public void actualizarPuntajes() throws JMSException{
		// Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        
        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // NO SE QUE HACER CON ESTO connection.setExceptionListener(this);

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("LealtadACME");

        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);

        
        // Recibo todos los mensajes disponibles
        Message message = consumer.receive(100);
        while (message != null){
        	// Descompongo el mensaje recibido
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("*** JMS: Mensaje recibido: " + text);
            
            // Agrego o actualizo el puntaje
            String puntos = String.valueOf(Integer.parseInt(text.split("-")[1])/100);
            MySQLConsultas.agregarPuntaje(text.split("-")[0], puntos);
	        
	        message = consumer.receive(100);
        }
        System.out.println("*** JMS: Puntajes actualizados");
        
        consumer.close();
        session.close();
        connection.close();
	}
}
