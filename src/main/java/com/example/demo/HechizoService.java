package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HechizoService {

    public static void createHechizo(Scanner scanner) {
        try {
            System.out.println("Nombre del hechizo :");
            String nombre = scanner.nextLine();
            System.out.println("Potencia (0-5) :");
            int potencia = Integer.parseInt(scanner.nextLine());
            System.out.println("Magia negra (true/false) :");
            boolean magiaNegra = scanner.nextLine().equalsIgnoreCase("S");

            String sql = "INSERT INTO hechizo (nombre, potencia, magia_negra) VALUES (?, ?, ?)";
            try (Connection conn = Database.getConnection(Main.URL); // Utilisation de Main.URL
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setInt(2, potencia);
                stmt.setBoolean(3, magiaNegra);
                stmt.executeUpdate();
                System.out.println("Hechizo creado con exito !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listHechizos() {
        try (Connection conn = Database.getConnection(Main.URL); // Utilisation de Main.URL
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM hechizo")) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("Lista de hechizos :");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int potencia = rs.getInt("potencia");
                boolean magiaNegra = rs.getBoolean("magia_negra");
                System.out.println("lNombre: " + nombre + ", Potencia: " + potencia + ", Magia negra: " + magiaNegra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteHechizo(String id) {
        String sqlSelect = "SELECT * FROM hechizo WHERE nombre = ?";
        String sqlDelete = "DELETE FROM hechizo WHERE nombre = ?";

        try (Connection conn = Database.getConnection(Main.URL)) {

            try (PreparedStatement selectStmt = conn.prepareStatement(sqlSelect)) {
                selectStmt.setString(1, id);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {

                    try (PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete)) {
                        deleteStmt.setString(1, id);
                        deleteStmt.executeUpdate();
                        System.out.println("Hechizo eliminado con exito !");
                    }
                } else {
                    System.out.println("Aucun hechizo trouvé avec cet ID !");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du hechizo : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
