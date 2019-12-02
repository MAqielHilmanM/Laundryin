package com.creative.iam.laundryin.views.order.confirm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.DoOrderResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.order.success.PesanBerhasilActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmPesananActivity extends AppCompatActivity {
    Button btnConfirm;
    TextView tvConfirmAddress,tvChangeAddress,tvConfirmDate,tvConfirmDay,tvConfirmPacket;
    EditText etConfirmCatatan;

    String paketId,username;

    private ProgressDialog pDialog;

    BottomSheetDialog sheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pesanan);

        initToolbar();
        initData();
        initListener();
    }

    public void showSheet(){
        sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setContentView(getLayoutInflater().inflate(R.layout.sheet_form,null));
        sheetDialog.show();

        TextView tvSheetTitle = sheetDialog.findViewById(R.id.tvSheetTitle);
        TextView tvSheetSkip = sheetDialog.findViewById(R.id.tvSheetSkip);
        final EditText etSheet = sheetDialog.findViewById(R.id.etSheet);
        Button btnSheet = sheetDialog.findViewById(R.id.btnSheet);

        tvSheetTitle.setText("Ubah Address");
        tvSheetSkip.setText("Batalkan");
        etSheet.setHint("Ketikkan alamat penjemputan baru");
        btnSheet.setText("Ganti Alamat");

        tvSheetSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSheet();
            }
        });

        btnSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvConfirmAddress.setText(etSheet.getText());
                hideSheet();
            }
        });
    }

    public void hideSheet(){
        sheetDialog.dismiss();
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
        tvConfirmAddress = findViewById(R.id.tvConfirmAddress);
        tvChangeAddress = findViewById(R.id.tvChangeAddress);
        tvConfirmDate = findViewById(R.id.tvConfirmDate);
        tvConfirmDay = findViewById(R.id.tvConfirmDay);
        tvConfirmPacket = findViewById(R.id.tvConfirmPacket);
        etConfirmCatatan = findViewById(R.id.etConfirmCatatan);

        paketId = getIntent().getStringExtra("paketId");
        String paketName = getIntent().getStringExtra("paketName");
        String paketEstimate = getIntent().getStringExtra("paketEstimate");

        PreferencesUtils sp = new PreferencesUtils(getApplicationContext());
        String address = sp.get("address",Constant.SAVED_ADDRESS);
        username = sp.get("uname", Constant.SAVED_USERNAME);

        tvConfirmPacket.setText(paketName);
        tvConfirmDay.setText(paketEstimate +" Hari");
        tvConfirmAddress.setText(address);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, Integer.valueOf(paketEstimate));
        Date todate1 = cal.getTime();
        String fromdate = dateFormat.format(todate1);

        tvConfirmDate.setText(fromdate);
    }

    private void initListener(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionOrder();
            }
        });

        tvChangeAddress.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSheet();
                    }
                }
        );
    }

    private void actionOrder(){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Buat Order ...");
        showDialog();


        ApiInterface api = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        api.doOrder(
                username,
                paketId,
                etConfirmCatatan.getText().toString(),
                tvConfirmAddress.getText().toString()
        ).enqueue(new Callback<BaseDao<DoOrderResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<DoOrderResponseDao>> call, Response<BaseDao<DoOrderResponseDao>> response) {
                hideDialog();
                if(response.body().getCode() == 1){
                    Toast.makeText(getApplicationContext(),"Berhasil Menambahkan order",Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(ConfirmPesananActivity.this, PesanBerhasilActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<DoOrderResponseDao>> call, Throwable t) {
                hideDialog();
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
