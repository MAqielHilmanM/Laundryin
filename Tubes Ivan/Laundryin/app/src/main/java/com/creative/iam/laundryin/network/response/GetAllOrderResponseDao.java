package com.creative.iam.laundryin.network.response;

public class GetAllOrderResponseDao {
    private String id_order;
    private String tgl_order;
    private String tgl_selesai;
    private String username;
    private String status;

    public GetAllOrderResponseDao(String id_order, String tgl_order, String tgl_selesai, String username, String status) {
        this.id_order = id_order;
        this.tgl_order = tgl_order;
        this.tgl_selesai = tgl_selesai;
        this.username = username;
        this.status = status;
    }

    public GetAllOrderResponseDao() {
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
