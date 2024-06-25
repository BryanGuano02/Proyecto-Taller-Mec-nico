/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudsqlserver.java;

/**
 *
 * @author kevin
 */import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Telefono_Quito {

    public String id_Empleado;
    public String numero_Telefonico;
    public String id_Taller;

    public Telefono_Quito(String id_Empleado, String numero_Telefonico, String id_Taller) {
        this.id_Empleado = id_Empleado;
        this.numero_Telefonico = numero_Telefonico;
        this.id_Taller = id_Taller;
    }

    // Métodos CRUD

    public void insertarTelefono(Connection conexion) {
        String sql = "INSERT INTO Telefono_Quito (id_Empleado, numero_Telefonico, id_Taller) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, id_Empleado);
            statement.setString(2, numero_Telefonico);
            statement.setString(3, id_Taller);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Teléfono insertado correctamente");
                JOptionPane.showMessageDialog(null, "Teléfono insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Telefono_Quito> obtenerTelefonos(Connection conexion) {
        List<Telefono_Quito> listaTelefonos = new ArrayList<>();

        String sql = "SELECT * FROM Telefono_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaTelefonos.add(new Telefono_Quito(
                        rs.getString("id_Empleado"),
                        rs.getString("numero_Telefonico"),
                        rs.getString("id_Taller")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaTelefonos(listaTelefonos);

        return listaTelefonos;
    }

    public void actualizarTelefono(Connection conexion, String nuevoNumero) {
        String sql = "UPDATE Telefono_Quito SET numero_Telefonico = ? WHERE id_Empleado = ? AND id_Taller = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevoNumero);
            statement.setString(2, id_Empleado);
            statement.setString(3, id_Taller);

            int registrosActualizados = statement.executeUpdate();

            if (registrosActualizados > 0) {
                System.out.println("Teléfono actualizado correctamente");
                JOptionPane.showMessageDialog(null, "Teléfono actualizado correctamente");
            } else {
                System.out.println("No se encontró el teléfono para actualizar");
                JOptionPane.showMessageDialog(null, "No se encontró el teléfono para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTelefono(Connection conexion) {
        String sql = "DELETE FROM Telefono_Quito WHERE id_Empleado = ? AND id_Taller = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, id_Empleado);
            statement.setString(2, id_Taller);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Teléfono eliminado correctamente");
                JOptionPane.showMessageDialog(null, "Teléfono eliminado correctamente");
            } else {
                System.out.println("No se encontró el teléfono para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró el teléfono para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaTelefonos(List<Telefono_Quito> listaTelefonos) {
        String cadena = "";

        for (Telefono_Quito telefono : listaTelefonos) {
            cadena = cadena + "ID Empleado: " + telefono.id_Empleado
                    + ", Número Telefónico: " + telefono.numero_Telefonico
                    + ", ID Taller: " + telefono.id_Taller + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}
