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

public class Articulo_Quito {

    public int id_articulo;
    public String id_taller;
    public String nombre_articulo;
    public String tipo_articulo;
    public String descripcion_articulo;
    public int cantidad_articulo;

    public Articulo_Quito(int id_articulo, String id_taller, String nombre_articulo, String tipo_articulo,
            String descripcion_articulo, int cantidad_articulo) {
        this.id_articulo = id_articulo;
        this.id_taller = id_taller;
        this.nombre_articulo = nombre_articulo;
        this.tipo_articulo = tipo_articulo;
        this.descripcion_articulo = descripcion_articulo;
        this.cantidad_articulo = cantidad_articulo;
    }

    // Métodos CRUD

    public void insertarArticulo(Connection conexion) {
        String sql = "INSERT INTO Articulo_Quito (id_articulo, id_taller, nombre_articulo, tipo_articulo, descripcion_articulo, cantidad_articulo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id_articulo);
            statement.setString(2, id_taller);
            statement.setString(3, nombre_articulo);
            statement.setString(4, tipo_articulo);
            statement.setString(5, descripcion_articulo);
            statement.setInt(6, cantidad_articulo);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Artículo insertado correctamente");
                JOptionPane.showMessageDialog(null, "Artículo insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Articulo_Quito> obtenerArticulos(Connection conexion) {
        List<Articulo_Quito> listaArticulos = new ArrayList<>();

        String sql = "SELECT * FROM Articulo_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaArticulos.add(new Articulo_Quito(
                        rs.getInt("id_articulo"),
                        rs.getString("id_taller"),
                        rs.getString("nombre_articulo"),
                        rs.getString("tipo_articulo"),
                        rs.getString("descripcion_articulo"),
                        rs.getInt("cantidad_articulo")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaArticulos(listaArticulos);

        return listaArticulos;
    }

    public void actualizarArticulo(Connection conexion, int nuevaCantidad) {
        String sql = "UPDATE Articulo_Quito SET cantidad_articulo = ? WHERE id_articulo = ? AND id_taller = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, id_articulo);
            statement.setString(3, id_taller);

            int registrosActualizados = statement.executeUpdate();

            if (registrosActualizados > 0) {
                System.out.println("Artículo actualizado correctamente");
                JOptionPane.showMessageDialog(null, "Artículo actualizado correctamente");
            } else {
                System.out.println("No se encontró el artículo para actualizar");
                JOptionPane.showMessageDialog(null, "No se encontró el artículo para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarArticulo(Connection conexion) {
        String sql = "DELETE FROM Articulo_Quito WHERE id_articulo = ? AND id_taller = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id_articulo);
            statement.setString(2, id_taller);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Artículo eliminado correctamente");
                JOptionPane.showMessageDialog(null, "Artículo eliminado correctamente");
            } else {
                System.out.println("No se encontró el artículo para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró el artículo para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaArticulos(List<Articulo_Quito> listaArticulos) {
        String cadena = "";

        for (Articulo_Quito articulo : listaArticulos) {
            cadena = cadena + "ID Artículo: " + articulo.id_articulo
                    + ", ID Taller: " + articulo.id_taller
                    + ", Nombre: " + articulo.nombre_articulo
                    + ", Tipo: " + articulo.tipo_articulo
                    + ", Descripción: " + articulo.descripcion_articulo
                    + ", Cantidad: " + articulo.cantidad_articulo + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}