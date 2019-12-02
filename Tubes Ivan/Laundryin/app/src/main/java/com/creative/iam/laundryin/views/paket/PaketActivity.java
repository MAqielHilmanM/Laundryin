package com.creative.iam.laundryin.views.paket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetAllPacketResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.order.confirm.ConfirmPesananActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        initData();
        initToolbar();
        initRecycler();
        initListener();
        loadData();
    }


    TextView tvTitleDetail,tvPaketPrice,tvDescDetail;
    Button btnPaketOrder;

    private void initData() {
        tvTitleDetail = findViewById(R.id.titleDetail);
        tvPaketPrice = findViewById(R.id.tvPaketPrice);
        tvDescDetail = findViewById(R.id.descDetail);
        btnPaketOrder = findViewById(R.id.btnPaketOrder);
    }


    List<PaketModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(){
        recyclerView = findViewById(R.id.rvPaket);
        recyclerView.setAdapter(new PaketAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    public void loadData(){
        String id = getIntent().getStringExtra("id");

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getPacket(id).enqueue(new Callback<BaseDao<GetAllPacketResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<GetAllPacketResponseDao>> call, Response<BaseDao<GetAllPacketResponseDao>> response) {
                if(response.body().getCode() == 1){
                    tvTitleDetail.setText(response.body().getData().getNama_paket());
                    Tools.loadTextFromHTML(tvPaketPrice,"Harga : <font color=\"#2398CD\"> Rp. "+response.body().getData().getHarga_paket()+"</font>/ Kg");
                    Tools.loadTextFromHTML(tvDescDetail,response.body().getData().getKeterangan());

                    lists.clear();
                    for (GetAllPacketResponseDao.UlasanResponseDao ulasan: response.body().getData().getUlasan()) {
                        lists.add(new PaketModel(
                                ulasan.getId(),
                                ulasan.getName(),
                                ulasan.getUlasan(),
                                ulasan.getEntry_date(),
                                ulasan.getUrl_picture()
                        ));
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Failed to get Detail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<GetAllPacketResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
