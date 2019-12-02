package com.creative.iam.laundryin.network.response;

public class GetOrderResponseDao {
    private String id_order;
    private String tgl_order;
    private String tgl_selesai;
    private String username;
    private String id_paket;
    private String catatan;
    private String address;
    private String status;
    private String nama_paket;
    private String harga_paket;
    private String estimasi_paket;
    private String keterangan;
    private String kode_transaksi;
    private String total_bayar;
    private String tgl_transaksi;

    public GetOrderResponseDao(String id_order, String tgl_order, String tgl_selesai, String username, String id_paket, String catatan, String address, String status, String nama_paket, String harga_paket, String estimasi_paket, String keterangan, String kode_transaksi, String total_bayar, String tgl_transaksi) {
        this.id_order = id_order;
        this.tgl_order = tgl_order;
        this.tgl_selesai = tgl_selesai;
        this.username = username;
        this.id_paket = id_paket;
        this.catatan = catatan;
        this.address = address;
        this.status = status;
        this.nama_paket = nama_paket;
        this.harga_paket = harga_paket;
        this.estimasi_paket = estimasi_paket;
        this.keterangan = keterangan;
        this.kode_transaksi = kode_transaksi;
        this.total_bayar = total_bayar;
        this.tgl_transaksi = tgl_transaksi;
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

    public String getId_paket() {
        return id_paket;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
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

    public String getKeterangan() {
        return keterangan;
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
