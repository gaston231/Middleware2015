package org.springframework;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;







import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
	 


	
	 
	 public class XSLTTest {
		 
		 
		 static final int constTipo1 = 1; //tipo ruta absoluta
		 static final int constTipo2 = 2; //tipo contenido
		 
		 //tipo = 1 - parametro es ruta del archivo
		 //tipo = 2 - parametro es contenido del archivo
		 

		 
		 //******************************************************************************
			
		// GENEXUS NEED		 
		 
		 public   String execute(int tipoXml , String entradaXML, int tipoXsl, String entradaXSL)  
		 {
			 ArrayList<String> rutasDeArchivos;
			 
			 String[] rutaArchivoXml = new String[1];
			 //Obtengo el nombre del XML
			 String nombreXml = nombreArchivoyRuta(entradaXML,rutaArchivoXml);
			
			 System.out.println(rutaArchivoXml[0]);
			 try
		     {
				 //tomo los tipos  y obtengo las rutas
			
				 rutasDeArchivos = rutasGeneradas(tipoXml,entradaXML,tipoXsl,entradaXSL);
					    


				 //recorremos la lista
				 
				 if (rutasDeArchivos.size() != 2)
					 
					 return "No se han encontrado dos rutas";
				 
				 
				 else{
					 
					 
					 transform(rutasDeArchivos.get(0), rutasDeArchivos.get(1), rutaArchivoXml[0] + nombreXml + "html");
					 
					 agregarCodigoHtml(rutaArchivoXml[0] + nombreXml + "html");
					 
					 
					 return "0";  //significa ok
					 
				 }
					 
		  
				 
		        
		     }
		     catch (TransformerConfigurationException e)
		     {
		    	 String retorno =  "error al transformar" + "TransformerConfigurationException";
		         System.err.println("TransformerConfigurationException");
		         System.err.println(e);
		         retorno += e;
		         return retorno;
		         
		     }
		     catch (TransformerException e)
		     {
		         System.err.println("TransformerException");
		         System.err.println(e);
		         
		         String retorno =  "error al transformar" + "TransformerException";		        
		         retorno += e;
		         return retorno;
		         
		     } catch (Exception e) {
				// TODO Auto-generated catch block
		    	 String retorno =  "error al transformar" + "TransformerException";		        
		         retorno += e;
		         return retorno;
			}
		}

		  private static void agregarCodigoBoton(PrintWriter buff)
		  {
			 		 			  			  
			  // Agrego el boton con su codigo 
			  String scriptFuncion = "<script language=\"JavaScript\">function doPrint(){";
			  scriptFuncion += "document.all.item(\"non-printable\").style.visibility='hidden';";
			  scriptFuncion += "window.print();document.all.item(\"non-printable\").style.visibility='visible';";
			  scriptFuncion += "}</script>";
				
			  String divNonPrintable = "<div id=\"non-printable\"><input type=\"submit\" name=\"wucRI1$btnPrint\" value=\"Imprimir\" id=\"wucRI1_btnPrint\" class=\"nice blue radius button\" onclick=\"doPrint();\" /></div>";
				

			  buff.println(scriptFuncion + divNonPrintable);
			  
			
			  
			  
		  }
              

		 
		 
		 private static void agregarCodigoHtml(String rutaOut) {
			
			 //tomo el archivo de salida y le agrego el boton para imprimir html
			 
			 PrintWriter out;
			try {
				 out = new PrintWriter(new BufferedWriter(new FileWriter(rutaOut, true)));
				
				 agregarCodigoBoton(out);				
				
				 out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		}






		//FIN GENEXUS NEED
		 //******************************************************************************
	
		  private static void transform(String dataXML, String inputXSL, String outputHTML)
			throws TransformerConfigurationException,TransformerException
		 {
		         TransformerFactory factory = TransformerFactory.newInstance();
		         StreamSource xslStream = new StreamSource(inputXSL);
		         Transformer transformer = factory.newTransformer(xslStream);
		         transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		         StreamSource in = new StreamSource(dataXML);
		         StreamResult out = new StreamResult(outputHTML);
		         transformer.transform(in, out);
		         System.out.println("The generated HTML file is:" + outputHTML);
		         
		         //Concateno el valor de el boton de imprimir html /javascript
		         
		        
				         
				         
		  }

		private static boolean esTipoRuta(int tipo)
		{
			
			return tipo ==constTipo1;
		}
		
		private static boolean esTipoContenido(int tipo)
		{
			
			return tipo ==constTipo2;
		}
		
	
		
		//Devuelve la ruta del archivo generado con el contenido
		private static String generarArchivo(String contenidoArchivo) throws Exception
		{
			
			File f = new File("tempArchivoBorrar");
			
			FileWriter w = new FileWriter(f);
				
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);  
				
			wr.write(contenidoArchivo);//escribimos en el archivo		
				
			wr.close();
				
			bw.close();
			
			return f.getCanonicalPath();
			
		}
		
		private static ArrayList<String> rutasGeneradas(int tipo1,String entrada1,int tipo2, String entrada2) throws Exception		
		{
			
		
						
			
				ArrayList<String> retornoListaRutas = new ArrayList<String>();				
				if (tipo1 == 0 || tipo2 == 0)
				{
					return null;
					
				}
				else { // asumnimos tipo 1 y 2 no son cero por lo que se ingresa algun valor "VALIDO"
					
					if (esTipoRuta(tipo1))
					{
						retornoListaRutas.add(entrada1);
						
					}
					else { //manejamos un archivo, guardamos su contenido y devolvemos la ruta
						
						if (esTipoContenido(tipo1))
						{
							String rutaGeneradaUno = generarArchivo(entrada1);
							retornoListaRutas.add(rutaGeneradaUno);
							
						}
		
					}
					
					//PREGUNTO PARA el sEGUNDO PARAMETRO
					if (esTipoRuta(tipo2))
					{
						retornoListaRutas.add(entrada2);
						
					}
					else { //manejamos un archivo, guardamos su contenido y devolvemos la ruta
						
						if (esTipoContenido(tipo2))
						{
							String rutaGeneradaUno = generarArchivo(entrada2);
							retornoListaRutas.add(rutaGeneradaUno);
							
						}
		
					}
					
					if (retornoListaRutas.size() != 2)
						return null;
					else 	
						
						return retornoListaRutas;
					
						
					
				}
			}
		
		  private static String nombreArchivoyRuta(String rutaCompleta, String[] ruta)
		  {
			  
			  int largoEntradaXMl = rutaCompleta.length();
				 String auxStr = rutaCompleta;
				 String busco = "\\";
				 int indice = 0;
				 int ultimaBarra = 0;
				 
				 //busco la posicion de la ultima /
				 while (indice < largoEntradaXMl) {				 
					 
					 while ( (indice < largoEntradaXMl) && !auxStr.substring(indice,indice+1).equals(busco)  )
					 {						 
						 indice++;
					 }
					 
					 if ( (indice < largoEntradaXMl) && (auxStr.substring(indice,indice+1).equals(busco)))
						 ultimaBarra = indice;
					 indice++;
				  }
				 
				 ruta[0] = auxStr.substring(0,ultimaBarra+1);	
				
				 System.out.println(ruta);
				 System.out.println(auxStr.substring(ultimaBarra+1, largoEntradaXMl-4));
				 return auxStr.substring(ultimaBarra+1, largoEntradaXMl-3);
		  }
		
	 	 
		 
	
	// public  static void main(String[] args) throws Exception
	// {
		
		// String err =  execute(Integer.parseInt(args[0]) , args[1] , Integer.parseInt(args[2]) , args[3] );  
		 
			
		 // System.out.println(err);
	    
	 // }
	
}	
	
	
