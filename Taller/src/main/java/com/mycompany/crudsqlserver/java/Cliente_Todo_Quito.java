/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Cliente_Todo_Quito {
    public String cedula_cliente;
    public String id_taller;
    public String nombre_Cliente; 
    public String apellido_cliente;
    public String ciudad_cliente;
    

 public Cliente_Todo_Quito ( String cedula_cliente , String id_taller ,String nombre_Cliente, String apellido_cliente , String ciudad_cliente) {
        this.cedula_cliente = cedula_cliente;
        this.id_taller = id_taller;
        this.nombre_Cliente = nombre_Cliente;
        this.apellido_cliente = apellido_cliente; 
        this.ciudad_cliente= ciudad_cliente;
        
    }

   public void insertarCliente(Connection conexion) {
        String sql = "INSERT INTO Cliente_Todo_Quito (cedula_cliente, id_taller, nombre_cliente, apellido_cliente, ciudad_cliente) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, cedula_cliente);
            statement.setString(2, id_taller);
            statement.setString(3, nombre_Cliente);
            statement.setString(4, apellido_cliente);
            statement.setString(5, ciudad_cliente);

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
        List<Cliente_Todo_Quito> listaClientes = new ArrayList<>();

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

    public void actualizarCliente(Connection conexion, String nuevaCiudad) {
        String sql = "UPDATE Cliente_Todo_Quito SET ciudad_cliente = ? WHERE nombre_cliente = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevaCiudad);
            statement.setString(2, nombre_Cliente);

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

    public void eliminarCliente(Connection conexion) {
        String sql = "DELETE FROM Cliente_Todo_Quito WHERE nombre_cliente = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre_Cliente);

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

    private static void mostrarListaClientes(List<Cliente_Todo_Quito> listaClientes) {
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
    
   