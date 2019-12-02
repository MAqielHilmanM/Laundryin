package com.creative.iam.laundryin.network.response;

public class DoUlasanResponseDao {
    private String id_ulasan;
    private String id_paket;
    private String username;
    private String ulasan;

    public String getId_ulasan() {
        return id_ulasan;
    }

    public String getId_paket() {
        return id_paket;
    }

    public String getUsername() {
        return username;
    }

    public String getUlasan() {
        return ulasan;
    }

    public DoUlasanResponseDao(String id_ulasan, String id_paket, String username, String ulasan) {
        this.id_ulasan = id_ulasan;
        this.id_paket = id_paket;
        this.username = username;
        this.ulasan = ulasan;
    }
}
