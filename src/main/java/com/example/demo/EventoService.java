package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EventoService {

    public static void createEvento(Scanner scanner) {
        try {
            System.out.println("Ingresar el nombre del evento :");
            String nombre = scanner.nextLine();
            System.out.println("Ingresar el profesor :");
            String profesor = scanner.nextLine();
            System.out.println("Ingresar el lugar :");
            String lugar = scanner.nextLine();
            System.out.println("Ingresar la fecha :");
            String fecha = scanner.nextLine();

            String sql = "INSERT INTO evento (nombre, profesor, lugar, fecha) VALUES (?, ?, ?, ?)";
            try (Connection conn = Database.getConnection(Main.URL); // Utilisation de Main.URL
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, profesor);
                stmt.setString(3, lugar);
                stmt.setString(4, fecha);
                stmt.executeUpdate();
                System.out.println("Evento creado con éxito !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listEventos() {
        try (Connection conn = Database.getConnection(Main.URL); // Utilisation de Main.URL
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM evento")) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Liste des événements :");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String profesor = rs.getString("profesor");
                String lugar = rs.getString("lugar");
                String fecha = rs.getString("fecha");
                System.out.println("Nombre: " + nombre + ", Profesor " + profesor + ", Lugar: " + lugar + ", Fecha: " + fecha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEvento(String id) {
        String sqlSelect = "SELECT * FROM evento WHERE nombre = ?";
        String sqlDelete = "DELETE FROM evento WHERE nombre = ?";

        try (Connection conn = Database.getConnection(Main.URL)) {

            try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect)) {
                selectStmt.setString(1, id);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {

                    try (PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {
                        deleteStmt.setString(1, id);
                        deleteStmt.executeUpdate();
                        System.out.println("Evento eliminado con exito !");
                    }
                } else {
                    System.out.println("No evento coneste nombre !");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du hechizo : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

