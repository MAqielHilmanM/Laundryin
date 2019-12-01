package com.creative.iam.laundryin.views.paket;

public class PaketModel{
    private String id;
    private String name;
    private String review;
    private String date;
    private String pic;

    public PaketModel(String id, String name, String review, String date, String pic) {
        this.id = id;
        this.name = name;
        this.review = review;
        this.date = date;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }

    public String getPic() {
        return pic;
    }
}