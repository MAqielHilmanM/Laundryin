package com.creative.iam.laundryin.views.order.success;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.order.confirm.ConfirmPesananActivity;

public class PesanBerhasilActivity extends AppCompatActivity {

    Button btnOke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_berhasil);

        initListener();
        initData();
    }

    private void initData(){
        btnOke = findViewById(R.id.btnOke);
    }

    private void initListener(){
        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
