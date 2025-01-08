package com.pbo.crud_mvc.model;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String role;
    private int fakultas;
    private String nim;
    public User(int idUser, String username, String password, String role, int fakultas, String nim) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fakultas = fakultas;
        this.nim = nim;
    }


    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getFakultas() {
        return fakultas;
    }
    public void setFakultas(int fakultas) {
        this.fakultas = fakultas;
    }


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
