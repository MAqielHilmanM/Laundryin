package com.creative.iam.laundryin.network.response;

public class GetOrderResponseDao {
    private String id_order;
    private String tgl_order;
    private String tgl_selesai;
    private String username;
    private String status;
    private String kode_transaksi;
    private String total_bayar;
    private String tgl_transaksi;

    public GetOrderResponseDao(String id_order, String tgl_order, String tgl_selesai, String username, String status, String kode_transaksi, String total_bayar, String tgl_transaksi) {
        this.id_order = id_order;
        this.tgl_order = tgl_order;
        this.tgl_selesai = tgl_selesai;
        this.username = username;
        this.status = status;
        this.kode_transaksi = kode_transaksi;
        this.total_bayar = total_bayar;
        this.tgl_transaksi = tgl_transaksi;
    }

    public GetOrderResponseDao() {
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

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }
}
