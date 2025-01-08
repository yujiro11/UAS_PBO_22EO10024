package com.pbo.crud_mvc.controller;

import com.pbo.crud_mvc.model.Anggota;
import com.pbo.crud_mvc.model.dao.AnggotaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class MainController {

    @FXML
    private TextField addAnggota_namaLengkapField;
    @FXML
    private TextField addAnggota_nimField;
    @FXML
    private ImageView gifImageView; // Tidak menggunakan `static`
    @FXML
    private TextField addAnggota_teleponField;
    @FXML
    private void addAnggota() {

        System.out.println("Anggota added!");
    }

    @FXML
    private TableView<Anggota> addAnggota_tableview;
    @FXML
    private TableColumn<Anggota, String> addAnggota_col_nomorUrut;
    @FXML
    private TableColumn<Anggota, String> addAnggota_col_nama;
    @FXML
    private TableColumn<Anggota, String> addAnggota_col_fakultas;
    @FXML
    private TableColumn<Anggota, String> addAnggota_col_nim;
    @FXML
    private TableColumn<Anggota, String> addAnggota_col_telepon;
    @FXML
    private ComboBox<String> addAnggota_fakultas;
    @FXML
    private Button addAnggota_addbtn;
    @FXML
    private Button addAnggota_clearbtn;
    @FXML
    private Button addAnggota_removebtn;
    private ObservableList<Anggota> anggotaList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        addAnggota_col_nama.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        addAnggota_col_fakultas.setCellValueFactory(cellData -> {
            int fakultas = cellData.getValue().getFakultas();
            return new SimpleStringProperty(mapIdTofakultas(fakultas));
        });
        addAnggota_col_nim.setCellValueFactory(cellData -> cellData.getValue().nimProperty());
        addAnggota_col_telepon.setCellValueFactory(cellData -> cellData.getValue().noTeleponProperty());

        addAnggota_col_nomorUrut.setCellValueFactory(cellData -> {
            int index = addAnggota_tableview.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });
        updateTable();

        addAnggota_fakultas.setItems(FXCollections.observableArrayList(
                "Fakultas Keguruan dan Ilmu Pendidikan (FKIP)",
                "Fakultas Matematika dan Komputer (FMIKOM)",
                "Fakultas Teknologi Industri (FTI)",
                "Fakultas Ekonomi (FE)",
                "Fakultas Keagamaan Islam (FKI)"
        ));


        addAnggota_clearbtn.setOnAction(event -> clearFields());
        addAnggota_addbtn.setOnAction(event -> handleAddAnggota());
        addAnggota_removebtn.setOnAction(event -> removeAnggota());
        addAnggota_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedAnggotaDetails(newValue);
            }
        });
        try {
            Image gifImage = new Image(getClass().getResource("Style/Paw.gif").toExternalForm());
            gifImageView.setImage(gifImage);
        } catch (Exception e){}


    }

    private void showSelectedAnggotaDetails(Anggota anggota) {
        addAnggota_namaLengkapField.setText(anggota.getNama());
        addAnggota_nimField.setText(anggota.getNim());
        addAnggota_teleponField.setText(anggota.getNoTelepon());
        addAnggota_fakultas.setValue(mapIdTofakultas(anggota.getFakultas()));
    }

    @FXML
    public void handleAddAnggota() {
        try {
            if (validateInput()) {
                String nama = addAnggota_namaLengkapField.getText();
                String nim = addAnggota_nimField.getText();
                String noTelepon = addAnggota_teleponField.getText();
                int fakultas = mapfakultasToId(addAnggota_fakultas.getValue());

                Anggota selectedAnggota = addAnggota_tableview.getSelectionModel().getSelectedItem();

                if (selectedAnggota == null) {
                    Anggota anggota = new Anggota(0, nama, nim, noTelepon, fakultas);
                    AnggotaDAO.addAnggota(anggota);
                    showAlert("Sukses", "Data MHS berhasil ditambahkan.", Alert.AlertType.INFORMATION);
                } else {
                    selectedAnggota.setNama(nama);
                    selectedAnggota.setNim(nim);
                    selectedAnggota.setNoTelepon(noTelepon);
                    selectedAnggota.setFakultas(fakultas);
                    AnggotaDAO.updateAnggota(selectedAnggota);
                    showAlert("Sukses", "Data MHS berhasil diperbarui.", Alert.AlertType.INFORMATION);
                }

                updateTable();
                clearFields();
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal menambah atau memperbarui data mhs: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void removeAnggota() {
        try {
            Anggota selectedAnggota = addAnggota_tableview.getSelectionModel().getSelectedItem();
            if (selectedAnggota != null) {
                AnggotaDAO.deleteAnggota(selectedAnggota.getIdAnggota());
                updateTable();
                clearFields();
                showAlert("Sukses", "Data MHS berhasil dihapus.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Peringatan", "Pilih data MHS yang ingin dihapus.", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal menghapus data MHS: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void updateTable() {
        try {
            anggotaList.setAll(AnggotaDAO.getAllAnggota());
            addAnggota_tableview.setItems(anggotaList);
        } catch (Exception e) {
            showAlert("Error", "Gagal memperbarui tabel: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private int mapfakultasToId(String fakultas) {
        switch (fakultas) {
            case "Fakultas Keguruan dan Ilmu Pendidikan (FKIP)": return 1;
            case "Fakultas Matematika dan Komputer (FMIKOM)": return 2;
            case "Fakultas Teknologi Industri (FTI)": return 3;
            case "Fakultas Ekonomi (FE)": return 4;
            case "Fakultas Keagamaan Islam (FKI)": return 5;
            default: throw new IllegalArgumentException("Fakultas tidak valid.");
        }
    }

    private String mapIdTofakultas(int fakultas) {
        switch (fakultas) {
            case 1: return "Fakultas Keguruan dan Ilmu Pendidikan (FKIP)";
            case 2: return "Fakultas Matematika dan Komputer (FMIKOM)";
            case 3: return "Fakultas Teknologi Industri (FTI)";
            case 4: return "Fakultas Ekonomi (FE)";
            case 5: return "Fakultas Keagamaan Islam (FKI)";
            default: return "Tidak Diketahui";
        }
    }

    private boolean validateInput() {
        if (addAnggota_fakultas.getValue() == null ||
                addAnggota_namaLengkapField.getText().trim().isEmpty() ||
                addAnggota_nimField.getText().trim().isEmpty() ||
                addAnggota_teleponField.getText().trim().isEmpty()) {
            showAlert("Input Error", "Semua field harus diisi.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void clearFields() {
        addAnggota_fakultas.setValue(null);
        addAnggota_namaLengkapField.clear();
        addAnggota_nimField.clear();
        addAnggota_teleponField.clear();
    }
}
