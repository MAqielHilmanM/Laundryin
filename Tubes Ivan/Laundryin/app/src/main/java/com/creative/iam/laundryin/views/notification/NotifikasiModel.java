package com.creative.iam.laundryin.views.notification;

public class NotifikasiModel {
    private String id;
    private String message;
    private String date;

    public NotifikasiModel(String id, String message, String date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}