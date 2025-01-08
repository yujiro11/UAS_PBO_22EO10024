package com.pbo.crud_mvc.model;
import javafx.beans.property.*;

public class Anggota {
    private final IntegerProperty idAnggota;
    private final StringProperty nama;
    private final StringProperty nim;
    private final StringProperty noTelepon;
    private final IntegerProperty fakultas;
    public Anggota(int idAnggota, String nama, String nim, String noTelepon, int fakultas) {
        this.idAnggota = new SimpleIntegerProperty(idAnggota);
        this.nama = new SimpleStringProperty(nama);
        this.nim = new SimpleStringProperty(nim);
        this.noTelepon = new SimpleStringProperty(noTelepon);
        this.fakultas = new SimpleIntegerProperty(fakultas);
    }


    public int getIdAnggota() {
        return idAnggota.get();
    }
    public void setIdAnggota(int idAnggota) {
        this.idAnggota.set(idAnggota);
    }
    public IntegerProperty idAnggotaProperty() {
        return idAnggota;
    }
    public String getNama() {
        return nama.get();
    }
    public void setNama(String nama) {
        this.nama.set(nama);
    }
    public StringProperty namaProperty() {
        return nama;
    }
    public String getNim() {
        return nim.get();
    }
    public void setNim(String nim) {
        this.nim.set(nim);
    }
    public StringProperty nimProperty() {
        return nim;
    }
    public String getNoTelepon() {
        return noTelepon.get();
    }
    public void setNoTelepon(String noTelepon) {
        this.noTelepon.set(noTelepon);
    }
    public StringProperty noTeleponProperty() {
        return noTelepon;
    }
    public int getFakultas() {
        return fakultas.get();
    }
    public void setFakultas(int fakultas) {
        this.fakultas.set(fakultas);
    }
    public IntegerProperty fakultasProperty() {
        return fakultas;
    }
}
