package org.springframework;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;


public class crearCSV {
	  public void parceXMLtoCSV(String input) throws Exception {
	        File stylesheet = new File("C:\\Middleware\\style.xsl");
	        File xmlSource = new File("C:\\Middleware\\in.xml");
	             
	        FileWriter fichero = new FileWriter("C:\\Middleware\\in.xml");
		    fichero.write(input + "\r\n");
		    fichero.close();
		   
		    
	//
	 //       DateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	 //       formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	  //      Date d = formatter.parse(s);
	      
	//
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(xmlSource);

	        StreamSource stylesource = new StreamSource(stylesheet);
	        Transformer transformer = TransformerFactory.newInstance()
	                .newTransformer(stylesource);
	       
	        //la entrada deberia ser fecha espacio hora espacio 
	        String[] fyh = document.getDocumentElement().getChildNodes().item(1).getNextSibling().getNextSibling().getFirstChild().toString().split("\\s");
	       // System.out.println( document.getDocumentElement().getChildNodes().item(1).getNextSibling().getNextSibling().getFirstChild().getNodeValue().toString());
	        System.out.println(fyh[1]);
	        System.out.println(fyh[2]);
	      
	        Source source = new DOMSource(document);
	        Path path = Paths.get("C:\\Middleware\\middleware-"+fyh[1].replace('/', '_')+'-'+fyh[2].replace(':', '_')+".csv");

	        Result outputTarget;
	        int i= 1;
	        if (Files.exists(path)) {
	          // file exist
	        	String s = path.toString().replace(".csv", "")+ "_v_";	
	        	System.out.println(s);
	        	while (Files.exists(Paths.get(s+i+".csv"))) i++;
	        	System.out.println(i);
	        		
	        //Random r = new Random();
	        	outputTarget = new StreamResult(new File("C:\\Middleware\\middleware-"+fyh[1].replace('/', '_')+'-'+fyh[2].replace(':', '_')+"_v_"+i+".csv"));
	        }

	        else{
	        	outputTarget = new StreamResult(new File("C:\\Middleware\\middleware-"+fyh[1].replace('/', '_')+'-'+fyh[2].replace(':', '_')+".csv"));
	        }
	        
	        
	        transformer.transform(source, outputTarget);
	        
	    }
	}

		