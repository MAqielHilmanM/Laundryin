package com.creative.iam.laundryin.views.main.home;

public class HomeModel{
    private String id;
    private String title;
    private String picture;

    public HomeModel(String id, String title, String picture) {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }
}