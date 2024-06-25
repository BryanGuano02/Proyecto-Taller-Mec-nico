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

public class Vehiculo_Matricula {

    public String num_matricula;

    public Vehiculo_Matricula(String num_matricula) {
        this.num_matricula = num_matricula;
    }

    // Métodos CRUD

    public void insertarMatricula(Connection conexion) {
        String sql = "INSERT INTO Vehiculo_Matricula (num_matricula) VALUES (?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, num_matricula);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Matrícula de vehículo insertada correctamente");
                JOptionPane.showMessageDialog(null, "Matrícula de vehículo insertada correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehiculo_Matricula> obtenerMatriculas(Connection conexion) {
        List<Vehiculo_Matricula> listaMatriculas = new ArrayList<>();

        String sql = "SELECT * FROM Vehiculo_Matricula";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaMatriculas.add(new Vehiculo_Matricula(
                        rs.getString("num_matricula")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaMatriculas(listaMatriculas);

        return listaMatriculas;
    }

    public void eliminarMatricula(Connection conexion) {
        String sql = "DELETE FROM Vehiculo_Matricula WHERE num_matricula = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, num_matricula);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Matrícula de vehículo eliminada correctamente");
                JOptionPane.showMessageDialog(null, "Matrícula de vehículo eliminada correctamente");
            } else {
                System.out.println("No se encontró la matrícula para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró la matrícula para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaMatriculas(List<Vehiculo_Matricula> listaMatriculas) {
        String cadena = "";

        for (Vehiculo_Matricula matricula : listaMatriculas) {
            cadena = cadena + "Matrícula de Vehículo: " + matricula.num_matricula + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}