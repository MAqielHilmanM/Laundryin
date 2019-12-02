package com.creative.iam.laundryin.views.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.paket.PaketAdapter;
import com.creative.iam.laundryin.views.paket.PaketModel;

import java.util.ArrayList;
import java.util.List;

public class NotifikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        initToolbar();
        initRecycler();
        loadDummy();
    }

    public void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    List<NotifikasiModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(){
        recyclerView = findViewById(R.id.rvNotifikasi);
        recyclerView.setAdapter(new NotifikasiAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    public void loadDummy(){
        lists.add(new NotifikasiModel("","Paket Hemat",""));
        lists.add(new NotifikasiModel("","Paket Hemat",""));
        lists.add(new NotifikasiModel("","Paket Hemat",""));
        lists.add(new NotifikasiModel("","Paket Hemat",""));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
