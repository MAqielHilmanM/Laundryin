package com.creative.iam.laundryin.views.notification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetAllOrderResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.main.profile.ProfileSubModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        initToolbar();
        initRecycler();
        loadData();
    }

    public void initToolbar() {
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

    public void initRecycler() {
        recyclerView = findViewById(R.id.rvNotifikasi);
        recyclerView.setAdapter(new NotifikasiAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }


    private void loadData() {
        PreferencesUtils sp = new PreferencesUtils(NotifikasiActivity.this);
        String username = sp.get("uname", Constant.SAVED_USERNAME);

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getAllOrder(username).enqueue(new Callback<BaseDao<List<GetAllOrderResponseDao>>>() {
            @Override
            public void onResponse(Call<BaseDao<List<GetAllOrderResponseDao>>> call, Response<BaseDao<List<GetAllOrderResponseDao>>> response) {
                if (response.body().getCode() == 1) {
                    lists.clear();
                    for (GetAllOrderResponseDao order : response.body().getData()
                    ) {
                        lists.add(new NotifikasiModel(
                                order.getId_order(),
                                Tools.loadMessage(order.getId_order(),Integer.valueOf(order.getStatus())),
                                order.getTgl_order()
                        ));
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(NotifikasiActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<List<GetAllOrderResponseDao>>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: " + t.getMessage());
                Toast.makeText(NotifikasiActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
