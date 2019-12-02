package com.creative.iam.laundryin.network.response;

public class DoDeliveryResponseDao {
    private String id_delivery;
    private String kode_transaksi;
    private String id_kurir;
    private String tgl_delivery;

    public DoDeliveryResponseDao(String id_delivery, String kode_transaksi, String id_kurir, String tgl_delivery) {
        this.id_delivery = id_delivery;
        this.kode_transaksi = kode_transaksi;
        this.id_kurir = id_kurir;
        this.tgl_delivery = tgl_delivery;
    }

    public DoDeliveryResponseDao() {
    }

    public String getId_delivery() {
        return id_delivery;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public String getId_kurir() {
        return id_kurir;
    }

    public String getTgl_delivery() {
        return tgl_delivery;
    }
}
