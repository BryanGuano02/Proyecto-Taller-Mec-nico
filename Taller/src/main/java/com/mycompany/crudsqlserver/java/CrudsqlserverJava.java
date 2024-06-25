/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.crudsqlserver.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class CrudsqlserverJava {

    public static void main(String[] args) {
        ConexionSQLServer con = new ConexionSQLServer();
        Connection conexion = con.obtenerConexion(); 
        
        
         //insertarCliente(conexion, "123456789", "T1", "Juan", "Perez", "Quito");

        // Ejemplo de obtener todos los clientes
     //   obtenerClientes(conexion);

        // Ejemplo de obtener un cliente específico
        //obtenerCliente(conexion, "Juan");

        // Ejemplo de actualizar un cliente
      //  actualizarCliente(conexion, "Juan", "Cuenca");

        // Ejemplo de eliminar un cliente
       // eliminarCliente(conexion, "Juan");

        // Volver a obtener todos los clientes después de las operaciones
       // obtenerClientes(conexion);
       
       // Cliente_Todo_Quito cliente = new Cliente_Todo_Quito("123456789", "1", "PEPE", "Perez", "Quito");
        
         //cliente.insertarCliente(conexion);
        
        Cliente_Todo_Quito.obtenerClientes(conexion);
        List<Vehiculo_Todo_Quito> obtenerVehiculos = Vehiculo_Todo_Quito.obtenerVehiculos(conexion);
        
      
    }
}

















































    
  /*   public static void insertarCliente(Connection conexion, String cedula, String idTaller, String nombre, String apellido, String ciudad) {
        String sql = "INSERT INTO Cliente_Todo_Quito (cedula_cliente, id_taller, nombre_cliente, apellido_cliente, ciudad_cliente) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement =  conexion.prepareStatement(sql);
            statement.setString(1, cedula);
            statement.setString(2, idTaller);
            statement.setString(3, nombre);
            statement.setString(4, apellido);
            statement.setString(5, ciudad);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Cliente insertado correctamente");
                JOptionPane.showMessageDialog(null, "Cliente insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cliente_Todo_Quito> obtenerClientes(Connection conexion) {
        listaClientes.clear();

        String sql = "SELECT * FROM Cliente_Todo_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaClientes.add(new Cliente_Todo_Quito(
                        rs.getString("cedula_cliente"),
                        rs.getString("id_taller"),
                        rs.getString("nombre_cliente"),
                        rs.getString("apellido_cliente"),
                        rs.getString("ciudad_cliente")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaClientes(listaClientes);

        return listaClientes;
    }

    public static void obtenerCliente(Connection conexion, String nombreCliente) {
        String sql = "SELECT * FROM Cliente_Todo_Quito WHERE nombre_cliente = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombreCliente);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println("Cedula: " + rs.getString("cedula_cliente")
                        + ", Id Taller: " + rs.getString("id_taller")
                        + ", Nombre: " + rs.getString("nombre_cliente")
                        + ", Apellido: " + rs.getString("apellido_cliente")
                        + ", Ciudad: " + rs.getString("ciudad_cliente"));

                JOptionPane.showMessageDialog(null, "Cedula: " + rs.getString("cedula_cliente")
                        + ", Id Taller: " + rs.getString("id_taller")
                        + ", Nombre: " + rs.getString("nombre_cliente")
                        + ", Apellido: " + rs.getString("apellido_cliente")
                        + ", Ciudad: " + rs.getString("ciudad_cliente"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarCliente(Connection conexion, String nombreCliente, String nuevaCiudad) {
        String sql = "UPDATE Cliente_Todo_Quito SET ciudad_cliente = ? WHERE nombre_cliente = ?";

        try {
            PreparedStatement statement = (PreparedStatement) conexion.prepareStatement(sql);
            statement.setString(1, nuevaCiudad);
            statement.setString(2, nombreCliente);

            int registrosActualizados = statement.executeUpdate();

            if (registrosActualizados > 0) {
                System.out.println("Cliente actualizado correctamente");
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
            } else {
                System.out.println("No se encontró el cliente para actualizar");
                JOptionPane.showMessageDialog(null, "No se encontró el cliente para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarCliente(Connection conexion, String nombreCliente) {
        String sql = "DELETE FROM Cliente_Todo_Quito WHERE nombre_cliente = ?";

        try {
            PreparedStatement statement = (PreparedStatement) conexion.prepareStatement(sql);
            statement.setString(1, nombreCliente);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Cliente eliminado correctamente");
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
            } else {
                System.out.println("No se encontró el cliente para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró el cliente para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarListaClientes(List<Cliente_Todo_Quito> listaClientes) {
        String cadena = "";

        for (Cliente_Todo_Quito cliente : listaClientes) {
            cadena = cadena + "Cedula: " + cliente.cedula_cliente
                    + ", Id Taller: " + cliente.id_taller
                    + ", Nombre: " + cliente.nombre_Cliente
                    + ", Apellido: " + cliente.apellido_cliente
                    + ", Ciudad: " + cliente.ciudad_cliente + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
} 
 */   
