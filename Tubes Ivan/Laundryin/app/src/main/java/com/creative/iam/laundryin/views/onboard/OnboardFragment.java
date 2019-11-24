package com.creative.iam.laundryin.views.onboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.iam.laundryin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardFragment extends Fragment {


    public OnboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboard, container, false);
    }

}
