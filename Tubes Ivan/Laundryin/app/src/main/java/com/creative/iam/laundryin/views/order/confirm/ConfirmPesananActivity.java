package com.creative.iam.laundryin.views.order.confirm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.order.success.PesanBerhasilActivity;

public class ConfirmPesananActivity extends AppCompatActivity {
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pesanan);

        initToolbar();
        initData();
        initListener();
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

    private void initData(){
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void initListener(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ConfirmPesananActivity.this, PesanBerhasilActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
