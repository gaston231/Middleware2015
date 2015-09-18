package com.midd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.mysql.MySQLConsultas;

/**
 * Servlet implementation class JMSServlet
 */
@WebServlet("/JMSServlet")
public class JMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JMSServlet() {
        super();
        // TODO Auto-generated constructor stub
		System.out.println("***** New Servlet *****");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("***** Inicialización del Servlet *****");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			actualizarPuntajes();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        response.setContentType("text/html");  
		System.out.println("***** Servlet POST *****");
		
		PrintWriter pw = response.getWriter();  
		pw.println("<h1>Puntajes acumulados en el sistema Pagos ACME</h1><br>");
		pw.println("<table id='puntaje' border=1><tr><td>Nombre</td><td>Puntos</td></tr>");
        ArrayList<String> puntajes = MySQLConsultas.getPuntajes();
        Iterator<String> iter = puntajes.iterator();
        while(iter.hasNext()){
        	String dato = iter.next();
        	pw.println("<tr><td>"+dato.split("-")[0]+"</td><td>"+dato.split("-")[1]+"</td></tr>");        	
        }
        pw.println("</table>");
        
        
        
        //request.getRequestDispatcher("/listar-puntaje.jsp").forward(request, response);
		
		 
	}
	
	private void actualizarPuntajes() throws JMSException{
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
        Message message = consumer.receive(10);
        while (message != null){
        	// Descompongo el mensaje recibido
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("Received1: " + text);
            
            // Agrego o actualizo el puntaje
            String puntos = String.valueOf(Integer.parseInt(text.split("-")[1])/100);
            MySQLConsultas.agregarPuntaje(text.split("-")[0], puntos);
	        
	        message = consumer.receive(10);
        }
        consumer.close();
        session.close();
        connection.close();
	}

}
