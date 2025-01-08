package com.pbo.crud_mvc.model.dao;

import com.pbo.crud_mvc.database.DatabaseConnection;
import com.pbo.crud_mvc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Fungsi untuk menambah user
    public static void addUser(User user) {
        String query = "INSERT INTO User (username, password, role, id_fakultas, nim) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getFakultas());
            stmt.setString(5, user.getNim());  // Mengganti email dengan nim
            stmt.executeUpdate();
            System.out.println("User berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk mengambil semua data user
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idUser = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                int fakultas = rs.getInt("fakultas");
                String nim = rs.getString("nim");  // Mengganti email dengan nim
                users.add(new User(idUser, username, password, role, fakultas, nim));  // Mengganti email dengan nim
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Fungsi untuk mengupdate data user
    public static void updateUser(User user) {
        String query = "UPDATE User SET username = ?, password = ?, role = ?, id_fakultas = ?, nim = ? WHERE id_user = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getFakultas());
            stmt.setString(5, user.getNim());  // Mengganti email dengan nim
            stmt.setInt(6, user.getIdUser());
            stmt.executeUpdate();
            System.out.println("User berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk menghapus user
    public static void deleteUser(int idUser) {
        String query = "DELETE FROM User WHERE id_user = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
            System.out.println("User berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
