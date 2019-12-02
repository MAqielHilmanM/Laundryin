package com.creative.iam.laundryin.views.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.iam.laundryin.R;

public class ProfileSubModel{
    private String id;
    private int status;

    public ProfileSubModel(String id, int status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

}
