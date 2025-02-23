package com.creative.iam.laundryin.network.response;

public class LoginResponseDao {
    private String username;
    private String nama;
    private String alamat;
    private String phone;
    private String pakaian;

    public LoginResponseDao(String username, String nama, String alamat, String phone, String pakaian) {
        this.username = username;
        this.nama = nama;
        this.alamat = alamat;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public String getPakaian() {
        return pakaian;
    }
}
