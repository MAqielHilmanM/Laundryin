package com.creative.iam.laundryin.network.response;

public class GetProfileResponseDao {
    private String username;
    private String nama;
    private String alamat;
    private String phone;
    private String pakaian;
    private String url_picture;

    public GetProfileResponseDao(String username, String nama, String alamat, String phone, String pakaian, String url_picture) {
        this.username = username;
        this.nama = nama;
        this.alamat = alamat;
        this.phone = phone;
        this.pakaian = pakaian;
        this.url_picture = url_picture;
    }

    public GetProfileResponseDao() {
    }

    public String getUrl_picture() {
        return url_picture;
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
