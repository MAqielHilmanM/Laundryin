package com.creative.iam.laundryin.network.response;

public class LoginResponseDao {
    private String username;
    private String nama;
    private String alamat;
    private String email;
    private String pakaian;

    public LoginResponseDao(String username, String nama, String alamat, String email, String pakaian) {
        this.username = username;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.pakaian = pakaian;
    }

    public LoginResponseDao() {
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getPakaian() {
        return pakaian;
    }
}
