package com.creative.iam.laundryin.views.paket.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.DoPacketResponseDao;
import com.creative.iam.laundryin.tools.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePacketActivity extends AppCompatActivity {

    EditText etNama,etPrice,etDescription,etTime;
    Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_packet);

        initData();
        initListener();

    }

    private void initData() {
        etNama = findViewById(R.id.etNama);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        etTime = findViewById(R.id.etTime);
        btnDaftar = findViewById(R.id.btnDaftar);
    }

    public void initListener(){
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPacket();
            }
        });
    }

    public void createPacket(){
        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.doPacket(
                etNama.getText().toString(),
                etPrice.getText().toString(),
                etTime.getText().toString(),
                etDescription.getText().toString(),
                ""
        ).enqueue(new Callback<BaseDao<DoPacketResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<DoPacketResponseDao>> call, Response<BaseDao<DoPacketResponseDao>> response) {
                if(response.body().getCode() == 1){
                    Toast.makeText(CreatePacketActivity.this.getApplicationContext(), "Berhasil Menambahkan Paket", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CreatePacketActivity.this.getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<DoPacketResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(CreatePacketActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
