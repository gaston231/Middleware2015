package middleware;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResultadoPago {
	Long idCliente;
	Long idCobro;
	Integer codigoResultado;
	String mensajeResultado;
	double montoPagado;
}
