package com.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.midd.DataPuntaje;

public class MySQLConsultas {

	public static ArrayList<DataPuntaje> getPuntajes()  {      
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null; 
        ArrayList<DataPuntaje> result = new ArrayList<DataPuntaje>(); 
        
        String query = "SELECT * FROM usuario";
        try {           
            connection = MySQLConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            while (rs.next()) {
            	DataPuntaje dato = new DataPuntaje();
                dato.setUsuario(rs.getString("idusuario"));
                dato.setPuntos(rs.getString("puntos"));
                result.add(dato);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
	
	public static void agregarPuntaje(String cliente, String puntos)  {      
        Connection connection = null;
        Statement statement = null; 
        String query = "";
        
        if (existeCliente(cliente)){
        	// Actualizo el puntaje del cliente
        	query = "UPDATE usuario SET puntos = puntos + " + puntos + " where idusuario = " + cliente + ";";
        } else {
        	// Agrego el puntaje del cliente nuevo
        	query = "INSERT INTO usuario VALUES (" + cliente + ", " + puntos + ");";
        }
        try {           
            connection = MySQLConnection.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
      
    }
	
	private static boolean existeCliente(String cliente){
		ResultSet rs = null;
        Connection connection = null;
        Statement statement = null; 
        boolean result = false; 
        
        String query = "SELECT * FROM usuario";
        try {           
            connection = MySQLConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            while (rs.next() && !result) {
            	result = (rs.getString("idusuario").equals(cliente));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
	}
}
