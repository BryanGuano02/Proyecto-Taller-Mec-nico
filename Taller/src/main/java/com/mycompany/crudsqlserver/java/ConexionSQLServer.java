/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudsqlserver.java;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class ConexionSQLServer {
    Connection conexion = null;
    
    String usuario = "sa";
    String contrasena = "P@ssw0rd";
    String db = "NODO_GUAYAQUIL";
    
    //String ip = "26.130.212.27";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection obtenerConexion() {
        try {
            String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";databaseName=" + db;
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "CONEXION EXITOSA");
            
        } catch (Exception e) {
            ///JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return conexion;
    }
}
