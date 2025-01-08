package com.pbo.crud_mvc.model.dao;

import com.pbo.crud_mvc.database.DatabaseConnection;
import com.pbo.crud_mvc.model.Anggota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnggotaDAO {

    // Fungsi untuk menambah anggota
    public static void addAnggota(Anggota anggota) {
        String query = "INSERT INTO Anggota (nama, nim, no_telepon, fakultas) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, anggota.getNama());
            stmt.setString(2, anggota.getNim());  // Changed from getEmail() to getNim()
            stmt.setString(3, anggota.getNoTelepon());
            stmt.setInt(4, anggota.getFakultas());
            stmt.executeUpdate();
            System.out.println("Data MHS berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk mengambil semua data anggota
    public static List<Anggota> getAllAnggota() {
        List<Anggota> anggotaList = new ArrayList<>();
        String query = "SELECT * FROM Anggota";
        try (Connection connection = DatabaseConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int idAnggota = rs.getInt("id_anggota");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");  // Changed from email to nim
                String noTelepon = rs.getString("no_telepon");
                int fakultas = rs.getInt("fakultas");
                anggotaList.add(new Anggota(idAnggota, nama, nim, noTelepon, fakultas));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anggotaList;
    }

    // Fungsi untuk mengupdate data anggota
    public static void updateAnggota(Anggota anggota) {
        String query = "UPDATE Anggota SET nama = ?, nim = ?, no_telepon = ?, fakultas = ? WHERE id_anggota = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, anggota.getNama());
            stmt.setString(2, anggota.getNim());  // Changed from getEmail() to getNim()
            stmt.setString(3, anggota.getNoTelepon());
            stmt.setInt(4, anggota.getFakultas());
            stmt.setInt(5, anggota.getIdAnggota());
            stmt.executeUpdate();
            System.out.println("Data MHS berhasil diupdate.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk menghapus anggota
    public static void deleteAnggota(int idAnggota) {
        String query = "DELETE FROM Anggota WHERE id_anggota = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAnggota);
            stmt.executeUpdate();
            System.out.println("Anggota berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk mencari anggota berdasarkan ID
    public static Anggota getAnggotaById(int idAnggota) {
        Anggota anggota = null;
        String query = "SELECT * FROM Anggota WHERE id_anggota = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAnggota);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");  // Changed from email to nim
                String noTelepon = rs.getString("no_telepon");
                int fakultas = rs.getInt("fakultas");
                anggota = new Anggota(idAnggota, nama, nim, noTelepon, fakultas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anggota;
    }
}
