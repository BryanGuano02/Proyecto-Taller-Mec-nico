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

public class Reparacion_Quito {

    public String num_matricula;
    public int id_reparacion;
    public int id_articulo;
    public String fecha_reparacion;
    public String tipo_reparacion;
    public String observacion;
    public double precio;
    public String id_taller;

    public Reparacion_Quito(String num_matricula, int id_reparacion, int id_articulo, String fecha_reparacion,
            String tipo_reparacion, String observacion, double precio, String id_taller) {
        this.num_matricula = num_matricula;
        this.id_reparacion = id_reparacion;
        this.id_articulo = id_articulo;
        this.fecha_reparacion = fecha_reparacion;
        this.tipo_reparacion = tipo_reparacion;
        this.observacion = observacion;
        this.precio = precio;
        this.id_taller = id_taller;
    }

    // Métodos CRUD

    public void insertarReparacion(Connection conexion) {
        String sql = "INSERT INTO Reparacion_Quito (num_matricula, id_reparacion, id_articulo, fecha_reparacion, tipo_reparacion, observacion, precio, id_taller) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, num_matricula);
            statement.setInt(2, id_reparacion);
            statement.setInt(3, id_articulo);
            statement.setString(4, fecha_reparacion);
            statement.setString(5, tipo_reparacion);
            statement.setString(6, observacion);
            statement.setDouble(7, precio);
            statement.setString(8, id_taller);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Reparación insertada correctamente");
                JOptionPane.showMessageDialog(null, "Reparación insertada correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Reparacion_Quito> obtenerReparaciones(Connection conexion) {
        List<Reparacion_Quito> listaReparaciones = new ArrayList<>();

        String sql = "SELECT * FROM Reparacion_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaReparaciones.add(new Reparacion_Quito(
                        rs.getString("num_matricula"),
                        rs.getInt("id_reparacion"),
                        rs.getInt("id_articulo"),
                        rs.getString("fecha_reparacion"),
                        rs.getString("tipo_reparacion"),
                        rs.getString("observacion"),
                        rs.getDouble("precio"),
                        rs.getString("id_taller")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaReparaciones(listaReparaciones);

        return listaReparaciones;
    }

    public void eliminarReparacion(Connection conexion) {
        String sql = "DELETE FROM Reparacion_Quito WHERE id_reparacion = ? AND id_taller = ? AND num_matricula = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id_reparacion);
            statement.setString(2, id_taller);
            statement.setString(3, num_matricula);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Reparación eliminada correctamente");
                JOptionPane.showMessageDialog(null, "Reparación eliminada correctamente");
            } else {
                System.out.println("No se encontró la reparación para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró la reparación para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaReparaciones(List<Reparacion_Quito> listaReparaciones) {
        String cadena = "";

        for (Reparacion_Quito reparacion : listaReparaciones) {
            cadena = cadena + "Número de Matrícula: " + reparacion.num_matricula
                    + ", ID de Reparación: " + reparacion.id_reparacion
                    + ", ID de Artículo: " + reparacion.id_articulo
                    + ", Fecha de Reparación: " + reparacion.fecha_reparacion
                    + ", Tipo de Reparación: " + reparacion.tipo_reparacion
                    + ", Observación: " + reparacion.observacion
                    + ", Precio: " + reparacion.precio
                    + ", ID de Taller: " + reparacion.id_taller + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}
