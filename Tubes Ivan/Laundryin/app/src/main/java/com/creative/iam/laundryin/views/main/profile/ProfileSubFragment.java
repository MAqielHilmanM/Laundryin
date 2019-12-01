package com.creative.iam.laundryin.views.main.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.main.home.HomeAdapter;
import com.creative.iam.laundryin.views.main.home.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileSubFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile_sub, container, false);

        initRecycler(v);
        loadDummy();

        return v;
    }


    List<ProfileSubModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(View v){
        recyclerView = v.findViewById(R.id.rvSubProfile);
        recyclerView.setAdapter(new ProfileSubAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void loadDummy(){
        lists.add(new ProfileSubModel("","",""));
        lists.add(new ProfileSubModel("","",""));
        lists.add(new ProfileSubModel("","",""));
        lists.add(new ProfileSubModel("","",""));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
