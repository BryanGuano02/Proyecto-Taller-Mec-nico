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

public class Empleado_Quito {

    public String id_Empleado;
    public String id_Taller;
    public String nombre_empleado;
    public String cedula_empleado;
    public String fecha_comienzo;
    public double salario;

    public Empleado_Quito(String id_Empleado, String id_Taller, String nombre_empleado, String cedula_empleado,
            String fecha_comienzo, double salario) {
        this.id_Empleado = id_Empleado;
        this.id_Taller = id_Taller;
        this.nombre_empleado = nombre_empleado;
        this.cedula_empleado = cedula_empleado;
        this.fecha_comienzo = fecha_comienzo;
        this.salario = salario;
    }

    // Métodos CRUD

    public void insertarEmpleado(Connection conexion) {
        String sql = "INSERT INTO Empleado_Quito (id_Empleado, id_Taller, Nombre_empleado, Cedula_Empleado, Fecha_comienzo, Salario) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, id_Empleado);
            statement.setString(2, id_Taller);
            statement.setString(3, nombre_empleado);
            statement.setString(4, cedula_empleado);
            statement.setString(5, fecha_comienzo);
            statement.setDouble(6, salario);

            int registrosAgregados = statement.executeUpdate();

            if (registrosAgregados > 0) {
                System.out.println("Empleado insertado correctamente");
                JOptionPane.showMessageDialog(null, "Empleado insertado correctamente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Empleado_Quito> obtenerEmpleados(Connection conexion) {
        List<Empleado_Quito> listaEmpleados = new ArrayList<>();

        String sql = "SELECT * FROM Empleado_Quito";

        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listaEmpleados.add(new Empleado_Quito(
                        rs.getString("id_Empleado"),
                        rs.getString("id_Taller"),
                        rs.getString("Nombre_empleado"),
                        rs.getString("Cedula_Empleado"),
                        rs.getString("Fecha_comienzo"),
                        rs.getDouble("Salario")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mostrarListaEmpleados(listaEmpleados);

        return listaEmpleados;
    }

    public void actualizarEmpleado(Connection conexion, double nuevoSalario) {
        String sql = "UPDATE Empleado_Quito SET Salario = ? WHERE id_Empleado = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setDouble(1, nuevoSalario);
            statement.setString(2, id_Empleado);

            int registrosActualizados = statement.executeUpdate();

            if (registrosActualizados > 0) {
                System.out.println("Empleado actualizado correctamente");
                JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente");
            } else {
                System.out.println("No se encontró el empleado para actualizar");
                JOptionPane.showMessageDialog(null, "No se encontró el empleado para actualizar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(Connection conexion) {
        String sql = "DELETE FROM Empleado_Quito WHERE id_Empleado = ?";

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, id_Empleado);

            int registrosEliminados = statement.executeUpdate();

            if (registrosEliminados > 0) {
                System.out.println("Empleado eliminado correctamente");
                JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente");
            } else {
                System.out.println("No se encontró el empleado para eliminar");
                JOptionPane.showMessageDialog(null, "No se encontró el empleado para eliminar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarListaEmpleados(List<Empleado_Quito> listaEmpleados) {
        String cadena = "";

        for (Empleado_Quito empleado : listaEmpleados) {
            cadena = cadena + "ID Empleado: " + empleado.id_Empleado
                    + ", ID Taller: " + empleado.id_Taller
                    + ", Nombre: " + empleado.nombre_empleado
                    + ", Cédula: " + empleado.cedula_empleado
                    + ", Fecha Comienzo: " + empleado.fecha_comienzo
                    + ", Salario: " + empleado.salario + "\n";
        }

        System.out.println(cadena);
        JOptionPane.showMessageDialog(null, cadena);
    }
}