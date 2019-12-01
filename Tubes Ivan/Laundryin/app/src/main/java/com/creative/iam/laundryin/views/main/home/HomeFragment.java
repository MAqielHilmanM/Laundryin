package com.creative.iam.laundryin.views.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView tvHomeTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        initData(view);
        initListener();
        initRecycler(view);
        loadDummy();

        return view;
    }

    List<HomeModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(View v){
        recyclerView = v.findViewById(R.id.rvHome);
        recyclerView.setAdapter(new HomeAdapter(lists));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    public void loadDummy(){
        lists.add(new HomeModel("","Paket Hemat",""));
        lists.add(new HomeModel("","Kiloan",""));
        lists.add(new HomeModel("","Paket 4 Hari",""));
        lists.add(new HomeModel("","Paket Kilat",""));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void initListener(){
        tvHomeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotifikasiActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void initData(View v){
        tvHomeTitle = v.findViewById(R.id.tvHomeTitle);
    }
}
