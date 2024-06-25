/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudsqlserver.java;

/**
 *
 * @author kevin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Clientes_Datos {

    public String nombre_cliente;
    public String apellido_cliente;

    public Clientes_Datos(String nombre_cliente, String apellido_cliente) {
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }

    // Métodos CRUD

    public void insertarClienteDatos(Connection conexion) {
        String sql = "INSERT INTO Clientes_Datos (nombre_cliente, apellido_cliente) VALUES (?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre_cliente);
            statement.setString(2, apellido_cliente);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Datos del cliente insertados correctamente");
                JOptionPane.showMessageDialog(null, "Datos del cliente insertados correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Clientes_Datos> obtenerClientesDatos(Connection conexion) {
        List<Clientes_Datos> listaClientesDatos = new ArrayList<>();

        String sql = "SELECT * FROM Clientes_Datos";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaClientesDatos.add(new Clientes_Datos(
                        rs.getString("nombre_cliente"),
                        rs.getString("apellido_cliente")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaClientesDatos(listaClientesDatos);

        return listaClientesDatos;
    }

    public void eliminarClienteDatos(Connection conexion) {
        String sql = "DELETE FROM Clientes_Datos WHERE nombre_cliente = ? AND apellido_cliente = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre_cliente);
            statement.setString(2, apellido_cliente);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Datos del cliente eliminados correctamente");
                JOptionPane.showMessageDialog(null, "Datos del cliente eliminados correctamente");
            } else {
                System.out.println("No se encontraron datos del cliente para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontraron datos del cliente para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaClientesDatos(List<Clientes_Datos> listaClientesDatos) {
        String cadena = "";

        for (Clientes_Datos clienteDatos : listaClientesDatos) {
            cadena = cadena + "Nombre del Cliente: " + clienteDatos.nombre_cliente
                    + ", Apellido del Cliente: " + clienteDatos.apellido_cliente + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}