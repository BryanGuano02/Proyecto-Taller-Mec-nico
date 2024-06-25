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

public class Vehiculo_Todo_Quito {

    public String num_matricula;
    public String id_taller;
    public String nombre_cliente;
    public String apellido_cliente;
    public String fecha_compra;
    public String marca;
    public String modelo;
    public int anio;

    public Vehiculo_Todo_Quito(String num_matricula, String id_taller, String nombre_cliente, String apellido_cliente,
            String fecha_compra, String marca, String modelo, int anio) {
        this.num_matricula = num_matricula;
        this.id_taller = id_taller;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.fecha_compra = fecha_compra;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    // Métodos CRUD

    public void insertarVehiculo(Connection conexion) {
        String sql = "INSERT INTO Vehiculo_Todo_Quito (num_matricula, id_taller, nombre_cliente, apellido_cliente, fecha_compra, marca, modelo, anio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, num_matricula);
            statement.setString(2, id_taller);
            statement.setString(3, nombre_cliente);
            statement.setString(4, apellido_cliente);
            statement.setString(5, fecha_compra);
            statement.setString(6, marca);
            statement.setString(7, modelo);
            statement.setInt(8, anio);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Vehículo insertado correctamente");
                JOptionPane.showMessageDialog(null, "Vehículo insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehiculo_Todo_Quito> obtenerVehiculos(Connection conexion) {
        List<Vehiculo_Todo_Quito> listaVehiculos = new ArrayList<>();

        String sql = "SELECT * FROM Vehiculo_Todo_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaVehiculos.add(new Vehiculo_Todo_Quito(
                        rs.getString("num_matricula"),
                        rs.getString("id_taller"),
                        rs.getString("nombre_cliente"),
                        rs.getString("apellido_cliente"),
                        rs.getString("fecha_compra"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaVehiculos(listaVehiculos);

        return listaVehiculos;
    }

    public void actualizarVehiculo(Connection conexion, String nuevaMarca, String nuevoModelo) {
        String sql = "UPDATE Vehiculo_Todo_Quito SET marca = ?, modelo = ? WHERE num_matricula = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevaMarca);
            statement.setString(2, nuevoModelo);
            statement.setString(3, num_matricula);

            int registrosActualizados = statement.executeUpdate();

            if (registrosActualizados > 0) {
                System.out.println("Vehículo actualizado correctamente");
                JOptionPane.showMessageDialog(null, "Vehículo actualizado correctamente");
            } else {
                System.out.println("No se encontró el vehículo para actualizar");
                JOptionPane.showMessageDialog(null, "No se encontró el vehículo para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVehiculo(Connection conexion) {
        String sql = "DELETE FROM Vehiculo_Todo_Quito WHERE num_matricula = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, num_matricula);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Vehículo eliminado correctamente");
                JOptionPane.showMessageDialog(null, "Vehículo eliminado correctamente");
            } else {
                System.out.println("No se encontró el vehículo para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró el vehículo para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaVehiculos(List<Vehiculo_Todo_Quito> listaVehiculos) {
        String cadena = "";

        for (Vehiculo_Todo_Quito vehiculo : listaVehiculos) {
            cadena = cadena + "Matrícula: " + vehiculo.num_matricula
                    + ", Id Taller: " + vehiculo.id_taller
                    + ", Nombre Cliente: " + vehiculo.nombre_cliente
                    + ", Apellido Cliente: " + vehiculo.apellido_cliente
                    + ", Fecha Compra: " + vehiculo.fecha_compra
                    + ", Marca: " + vehiculo.marca
                    + ", Modelo: " + vehiculo.modelo
                    + ", Año: " + vehiculo.anio + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}