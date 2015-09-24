import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

class Xml2Csv {

    public static void main(String args[]) throws Exception {
        File stylesheet = new File("src/style.xsl");
        File xmlSource = new File("src/Pago.xml");
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
        Result outputTarget = new StreamResult(new File("src/middleware-"+fyh[1]+'-'+fyh[2].replace(':', '_')+".csv"));
        transformer.transform(source, outputTarget);
        
    }
}

	