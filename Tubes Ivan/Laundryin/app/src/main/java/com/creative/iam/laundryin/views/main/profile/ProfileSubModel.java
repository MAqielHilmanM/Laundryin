package com.creative.iam.laundryin.views.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.iam.laundryin.R;

public class ProfileSubModel{
    private String id;
    private String status;
    private String pictures;

    public ProfileSubModel(String id, String status, String pictures) {
        this.id = id;
        this.status = status;
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPictures() {
        return pictures;
    }
}
