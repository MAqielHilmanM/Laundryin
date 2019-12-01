package com.creative.iam.laundryin.views.paket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.order.confirm.ConfirmPesananActivity;

import java.util.ArrayList;
import java.util.List;

public class PaketActivity extends AppCompatActivity {
    TextView titleDetail;
    Button btnPaketOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);
        titleDetail = (TextView) findViewById(R.id.titleDetail);
        btnPaketOrder = findViewById(R.id.btnPaketOrder);

        initRecycler();
        loadDummy();
        initListener();
    }

    List<PaketModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(){
        recyclerView = findViewById(R.id.rvHome);
        recyclerView.setAdapter(new PaketAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    public void loadDummy(){
        lists.add(new PaketModel("","Paket Hemat","","",""));
        lists.add(new PaketModel("","Paket Hemat","","",""));
        lists.add(new PaketModel("","Paket Hemat","","",""));
        lists.add(new PaketModel("","Paket Hemat","","",""));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void initData(){

    }

    public void initListener(){
        btnPaketOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaketActivity.this, ConfirmPesananActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
