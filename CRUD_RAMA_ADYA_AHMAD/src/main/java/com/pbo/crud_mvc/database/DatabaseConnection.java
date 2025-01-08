package com.pbo.crud_mvc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_mahasiswa";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    public static Connection connect() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();
        }
        return connection;
    }
}
