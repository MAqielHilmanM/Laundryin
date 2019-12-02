package com.creative.iam.laundryin.network.response;

public class DoTransactionResponseDao {
    private String kode_transaksi;
    private String id_order;
    private String total_bayar;
    private String tgl_transaksi;

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public String getId_order() {
        return id_order;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public DoTransactionResponseDao() {
    }

    public DoTransactionResponseDao(String kode_transaksi, String id_order, String total_bayar, String tgl_transaksi) {
        this.kode_transaksi = kode_transaksi;
        this.id_order = id_order;
        this.total_bayar = total_bayar;
        this.tgl_transaksi = tgl_transaksi;
    }
}
