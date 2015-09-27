package middleware;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class ResultadoVenta {
	Long idCliente;
	Long idCobro;
	ArrayList<String> codigosDeEntrada;
	Integer codigoResultado;
	String mensajeResultado;
	double montoPagado;
}
