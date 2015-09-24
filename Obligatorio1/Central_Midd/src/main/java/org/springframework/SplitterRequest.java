package org.springframework;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;






import javax.xml.transform.Result;


import org.w3c.dom.Document;




public class SplitterRequest {

	
	
	
	public ArrayList<String> split(String input) throws Exception {
		
		ArrayList<String> retorno = new ArrayList<String>();
 		//Uso XSLT o XPATH para parsear la entrada de el request de entrada
		
		// CONVERSION DE REQUEST ENTRADA a ARCHIVO 
		FileWriter fichero = new FileWriter("C:\\Users\\cvelez.MGAP\\Desktop\\Midd2015\\Middleware2015\\Obligatorio1\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\inputXML.xml");

	    fichero.write(input + "\r\n");
	    fichero.close();

		File stylesheet = new File("C:\\Users\\cvelez.MGAP\\Desktop\\Midd2015\\Middleware2015\\Obligatorio1\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\request.xsl");
        File xmlSource = new File("C:\\Users\\cvelez.MGAP\\Desktop\\Midd2015\\Middleware2015\\Obligatorio1\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\inputXML.xml");
        String salida = "C:\\Users\\cvelez.MGAP\\Desktop\\Midd2015\\Middleware2015\\Obligatorio1\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\salida.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);

        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(salida));
        transformer.transform(source, outputTarget);
			
        // ENVIAMOS LA SALIDA DEL SPLITTER AL ROUTER
        
        Pattern p = Pattern.compile("(<PagarFactura.*?</PagarFactura> | <ventaEntradas.*?</ventaEntradas> )",Pattern.CASE_INSENSITIVE);
      
        String str = "";
        FileReader f = new FileReader(salida);
        BufferedReader b = new BufferedReader(f);
        String cadena = b.readLine();
        while(!(cadena == null)) {            
        	str += cadena;
        	cadena = b.readLine();
        }
        b.close();
        str = str.replaceAll("\\r|\\n|\\t", "");
    
        System.out.println(str);
        Matcher m = p.matcher(str);
        int count = 0;
        while(m.find()) {
        	retorno.add(m.group(1).toString());
            System.out.println("found: " + m.group(1).toString());
        }
        
		return retorno;

	}
}
