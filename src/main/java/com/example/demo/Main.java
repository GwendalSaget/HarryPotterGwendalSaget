package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:sqlite:C:/Users/gwend/OneDrive/Documents/UAX/Concurrente/demo/src/main/java/com/example/demo/database.db";
    static final String URL2 = "jdbc:sqlite:C:/Users/gwend/OneDrive/Documents/UAX/Concurrente/demo/src/main/java/com/example/demo/database2.db";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn2 = DriverManager.getConnection(URL2)) {
            System.out.println("¡Conexión exitosa a la base de datos 2!");
            Database.initDatabase2(conn2);
            if (!areUsersPresent(conn2)) {
                createUser(scanner, conn2);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos 2: " + e.getMessage());
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("¡Conexión exitosa a la base de datos!");
            Database.initDatabase(conn);
            Connection conn3 = DriverManager.getConnection(URL2);
            if (!authenticateUser(scanner, conn3)) {
                System.out.println("Acceso denegado. Nombre de usuario o contraseña incorrectos.");
                return;
            }

            mainMenu(scanner, conn);
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static boolean areUsersPresent(Connection conn) throws SQLException {
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT COUNT(*) FROM usuarios");
        rs.next();
        return rs.getInt(1) > 0;
    }

    private static void createUser(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Por favor, ingrese un nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Por favor, ingrese una contraseña:");
        String password = scanner.nextLine();

        String sql = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("¡Usuario creado con éxito!");
        }
    }

    private static boolean authenticateUser(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("Por favor, ingrese su nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Por favor, ingrese su contraseña:");
        String password = scanner.nextLine();

        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND password = ?";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            var rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0; // Retourne vrai si l'utilisateur existe
        }
    }

    private static void mainMenu(Scanner scanner, Connection conn) throws SQLException {
        while (true) {
            System.out.println("Elige una opción :");
            System.out.println("H - Crear un hechizo");
            System.out.println("E - Crear un evento");
            System.out.println("L - Ver la lista de hechizos y eventos");
            System.out.println("1 - Suprimir un hechizo");
            System.out.println("2 - Suprimir un evento");
            System.out.println("U - Eliminar un usuario");
            System.out.println("S - Salir");

            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "H":
                    HechizoService.createHechizo(scanner);
                    break;
                case "E":
                    EventoService.createEvento(scanner);
                    break;
                case "L":
                    HechizoService.listHechizos();
                    EventoService.listEventos();
                    break;
                case "1":
                    System.out.println("Ingresar el nombre del elemento a eliminar :");
                    try {
                        String id = scanner.nextLine();
                        HechizoService.deleteHechizo(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Nombre invalido. Ingresar un nombre valido.");
                    }
                    break;
                case "U":
                    Connection conn4 = DriverManager.getConnection(URL2);
                    UsuarioService us = new UsuarioService();
                    us.deleteUser(conn4);
                    break;
                case "2":
                    System.out.println("Ingresar el nombre del elemento a eliminar :");
                    try {
                        String id = scanner.nextLine();
                        EventoService.deleteEvento(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Nombre invalido. Ingresar un nombre valido.");
                    }
                    break;
                case "S":
                    System.out.println("Ciao !");
                    return;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        }
    }
}
