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
	        File stylesheet = new File("src/main/resources/META-INF/integration/style.xsl");
	        
	        File xmlSource = new File("src/main/resources/META-INF/integration/input.xml");        
	        FileWriter fichero = new FileWriter("src/main/resources/META-INF/integration/input.xml");
		    fichero.write(input + "\r\n");
		    fichero.close();
	         
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
	      //  System.out.println(fyh[1]);
	      //  System.out.println(fyh[2]);
	      
	        Source source = new DOMSource(document);
	        Path path = Paths.get("src/middleware-"+fyh[1].replace('/', '-')+'-'+fyh[2].replace(':', '_')+".csv");

	        Result outputTarget;
	        if (Files.exists(path)) {
	          // file exist
	        	Random r = new Random();
	        	outputTarget = new StreamResult(new File("src/middleware-"+fyh[1].replace('/', '-')+'-'+fyh[2].replace(':', '_')+"_v_"+r.nextInt(10)+".csv"));
	        }

	        else{
	        	outputTarget = new StreamResult(new File("src/middleware-"+fyh[1].replace('/', '-')+'-'+fyh[2].replace(':', '_')+".csv"));
	        }
	        
	        
	        transformer.transform(source, outputTarget);
	        
	    }
	}

		