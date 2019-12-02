package com.creative.iam.laundryin.network.response;

import java.util.List;

public class GetAllPacketResponseDao {
    private String id_paket;
    private String nama_paket;
    private String harga_paket;
    private String estimasi_paket;
    private String keterangan;
    private String url_picture;
    private List<UlasanResponseDao> ulasan;

    public GetAllPacketResponseDao(String id_paket, String nama_paket, String harga_paket, String estimasi_paket, String keterangan, String url_picture, List<UlasanResponseDao> ulasan) {
        this.id_paket = id_paket;
        this.nama_paket = nama_paket;
        this.harga_paket = harga_paket;
        this.estimasi_paket = estimasi_paket;
        this.keterangan = keterangan;
        this.url_picture = url_picture;
        this.ulasan = ulasan;
    }

    public GetAllPacketResponseDao() {
    }

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

    public String getKeterangan() {
        return keterangan;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public List<UlasanResponseDao> getUlasan() {
        return ulasan;
    }

    public class UlasanResponseDao{
        private String id;
        private String id_paket;
        private String username;
        private String ulasan;
        private String entry_date;
        private String name;
        private String url_picture;

        public UlasanResponseDao(String id, String id_paket, String username, String ulasan, String entry_date, String name, String url_picture) {
            this.id = id;
            this.id_paket = id_paket;
            this.username = username;
            this.ulasan = ulasan;
            this.entry_date = entry_date;
            this.name = name;
            this.url_picture = url_picture;
        }

        public UlasanResponseDao() {
        }

        public String getId() {
            return id;
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

        public String getEntry_date() {
            return entry_date;
        }

        public String getName() {
            return name;
        }

        public String getUrl_picture() {
            return url_picture;
        }
    }
}
