<%@page import="javax.jms.JMSException"%>
<%@page import="com.midd.DataPuntaje,
				java.util.LinkedList,
				com.mysql.MySQLConsultas,
				java.util.ArrayList,
				com.midd.JMS"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema de lealtad - Pagos ACME - Puntajes</title>
</head>
<body>
	<h2>Listado de puntajes de los usuarios de PagosACME</h2>
	<br>
	<div class=table-wrapper>
		<table id=tabla-puntajes>
			<thead>
				<tr>
					<th>Usuario</th>
					<th>Puntaje acumulado</th>
				</tr>							
			</thead>
		
			<%
				JMS jms = new JMS();
				try{
					jms.actualizarPuntajes();
				} catch (JMSException ex){
					
				}
				ArrayList<DataPuntaje> listdata = MySQLConsultas.getPuntajes();
				for (DataPuntaje dp : listdata){
				%>
					<tr>
					<td> <%= dp.getUsuario() %></td>
					<td> <%= dp.getPuntos() %></td>
					</tr>
				
				<%}%>
		</table>
	</div>
	
</body>
</html>