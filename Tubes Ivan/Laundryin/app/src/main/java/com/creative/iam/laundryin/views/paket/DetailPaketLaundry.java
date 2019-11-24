package com.creative.iam.laundryin.views.paket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.creative.iam.laundryin.R;

public class DetailPaketLaundry extends AppCompatActivity {
    TextView titleDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket_laundry);
        Intent intent = getIntent();
        String paket = intent.getStringExtra("paket");
        titleDetail = (TextView) findViewById(R.id.titleDetail);
    }

    public void checkPaket(String paket){
        if(paket.equalsIgnoreCase("paketHemat")){
            titleDetail.setText("Paket Hemat");
        }else if(paket.equalsIgnoreCase("kiloan")){
            titleDetail.setText("Paket Kiloan");
        }else if(paket.equalsIgnoreCase("empatHari")){
            titleDetail.setText("Paket 4 Hari");
        }else if(paket.equalsIgnoreCase("paketKilat")){
            titleDetail.setText("Paket Kilat");
        }
    }
}
