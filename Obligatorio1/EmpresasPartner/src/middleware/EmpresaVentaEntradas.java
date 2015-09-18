package middleware;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService
public class EmpresaVentaEntradas {
    
	WebServiceContext wsctx;
	@WebMethod
	public ResultadoVenta ventaEntrada(Short cantidadEntradas, String codigoMoneda, double precioUnitario, Date fechaHora){
		ResultadoVenta result = new ResultadoVenta();
		MessageContext mctx = wsctx.getMessageContext();

		// Use the request headers to get the details
		Map http_headers =
			(Map) mctx.get(
			MessageContext.HTTP_REQUEST_HEADERS);
		List<String> userList = (List) http_headers.get("Username");
		List<String> passList = (List) http_headers.get("Password");

		String username = "";
		String password = "";

		
		System.out.println("Mostrando datos de autenticacion...");
		if (userList != null) {
			username = userList.get(0);
			System.out.println("username>"+username);
		}

		if (passList != null) {
			password = passList.get(0);
			System.out.println("pass>"+password);
		}

		if (username.equals("user")
			&&
			password.equals("pass")) {
			result.idCobro = (long) -9999;
		} else {
			result.idCobro = (long) 1000;
		}
		result.codigosDeEntrada = new ArrayList<String>();
		result.codigosDeEntrada.add("pass>"+password+";"+"username>"+username);
		
		return result;
    }

}
