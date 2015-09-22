package org.springframework;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Splitter;

public class SplitterRequest {

	
	
	@Bean
	public String split(String input) throws TransformerException {
		
		//Uso XSLT o XPATH para parsear la entrada de el request de entrada
		
		String dataXML = "C:\\Users\\cvelez.MGAP\\workspaceMars\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\inputXML.xml";
				;
		String inputXSL = "C:\\Users\\cvelez.MGAP\\workspaceMars\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\request.xsl";
		String outputHTML = "C:\\Users\\cvelez.MGAP\\workspaceMars\\Central_Midd\\src\\main\\resources\\META-INF\\integration\\request.html";
		
		
		XSLTTest test = new XSLTTest();
		
		test.execute(1, dataXML, 1,inputXSL );
		
		
		System.out.println(outputHTML);
		return outputHTML;
		
		

	}
}
