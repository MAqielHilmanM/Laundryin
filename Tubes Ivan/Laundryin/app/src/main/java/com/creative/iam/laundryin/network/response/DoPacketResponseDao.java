package com.creative.iam.laundryin.network.response;

public class DoPacketResponseDao {
    private String id_paket;
    private String nama_paket;
    private String harga_paket;
    private String estimasi_paket;
    private String url_picture;

    public String getId_paket() {
        return id_paket;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public String getHarga_paket() {
        return harga_paket;
    }

    public String getEstimasi_paket() {
        return estimasi_paket;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public DoPacketResponseDao(String id_paket, String nama_paket, String harga_paket, String estimasi_paket, String url_picture) {
        this.id_paket = id_paket;
        this.nama_paket = nama_paket;
        this.harga_paket = harga_paket;
        this.estimasi_paket = estimasi_paket;
        this.url_picture = url_picture;
    }
}
