package middleware;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

@WebService
@SchemaValidation
public class EmpresaVentaEntradas {
	static Integer cantidadEntradasDisponibles = 20;
	static Long idCobro = (long) 0;
	static Integer idEntradas = 100000;
	
	@WebMethod
	public ResultadoVenta ventaEntrada(Short cantidadEntradas, String codigoMoneda, double precioUnitario, Date fechaHora) throws CantidadNoDisponible{
		ResultadoVenta result = new ResultadoVenta();
		result.codigosDeEntrada = new ArrayList<String>();
		if (cantidadEntradas > cantidadEntradasDisponibles){
            throw new CantidadNoDisponible("Cantidad no disponible. Máximo disponible: " + cantidadEntradasDisponibles);
		}
		for (int i = 1; i <= cantidadEntradas; i++){
			result.codigosDeEntrada.add(idEntradas.toString());
			idEntradas++;
		}
		idCobro++;
		result.idCobro = idCobro;
		return result;
    }

}
