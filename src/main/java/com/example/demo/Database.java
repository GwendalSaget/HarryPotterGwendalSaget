package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    public static void initDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS hechizo (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), potencia INT, magia_negra BOOLEAN)");
            stmt.execute("CREATE TABLE IF NOT EXISTS evento (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), profesor VARCHAR(255), lugar VARCHAR(255), fecha VARCHAR(255))");
        }
    }
    public static void initDatabase2(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (id BIGINT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), password VARCHAR(255))");
        }
    }

}

