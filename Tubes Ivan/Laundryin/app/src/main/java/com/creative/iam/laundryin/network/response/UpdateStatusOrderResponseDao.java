package com.creative.iam.laundryin.network.response;

public class UpdateStatusOrderResponseDao {
    private String id_order;
    private String tgl_order;
    private String tgl_selesai;
    private String username;
    private String id_paket;
    private String catatan;
    private String address;
    private String status;

    public String getId_paket() {
        return id_paket;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getAddress() {
        return address;
    }

    public UpdateStatusOrderResponseDao(String id_order, String tgl_order, String tgl_selesai, String username, String id_paket, String catatan, String address, String status) {
        this.id_order = id_order;
        this.tgl_order = tgl_order;
        this.tgl_selesai = tgl_selesai;
        this.username = username;
        this.id_paket = id_paket;
        this.catatan = catatan;
        this.address = address;
        this.status = status;
    }

    public UpdateStatusOrderResponseDao() {
    }

    public String getId_order() {
        return id_order;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }
}
