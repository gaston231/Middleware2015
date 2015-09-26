package middleware;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

@WebService
@SchemaValidation
public class EmpresaVentaEntradas {
	static Integer cantidadEntradasDisponibles = 200;
	static Long idCobro = (long) 0;
	static Integer idEntradas = 100000;
	
	@WebMethod
	public ResultadoVenta ventaEntrada(Short cantidadEntradas, String codigoMoneda, double precioUnitario, Date fechaHora) throws CantidadNoDisponible{
		ResultadoVenta resultado = new ResultadoVenta();
		resultado.codigosDeEntrada = new ArrayList<String>();
		Integer idCobroError = -1;
		
		// Verifico disponibilidad de entradas
		if (cantidadEntradas > cantidadEntradasDisponibles){
            //throw new CantidadNoDisponible("Cantidad no disponible. Máximo disponible: " + cantidadEntradasDisponibles);
			resultado.codigoResultado = -1;
			resultado.mensajeResultado = "Error: Cantidad no disponible. Máximo disponible: " + cantidadEntradasDisponibles;
			resultado.idCobro = idCobroError.longValue();
		} else {
			for (int i = 1; i <= cantidadEntradas; i++){
				resultado.codigosDeEntrada.add(idEntradas.toString());
				idEntradas++;
			}
			idCobro++;
			resultado.idCobro = idCobro;
			resultado.codigoResultado = 0;
			resultado.mensajeResultado = "OK";
		}
		return resultado;
    }

}
