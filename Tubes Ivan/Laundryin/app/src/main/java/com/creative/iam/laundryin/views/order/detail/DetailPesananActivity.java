package com.creative.iam.laundryin.views.order.detail;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.DoUlasanResponseDao;
import com.creative.iam.laundryin.network.response.GetAllOrderResponseDao;
import com.creative.iam.laundryin.network.response.GetAllPacketResponseDao;
import com.creative.iam.laundryin.network.response.GetOrderResponseDao;
import com.creative.iam.laundryin.network.response.UpdateStatusOrderResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;
import com.creative.iam.laundryin.views.notification.NotifikasiModel;
import com.creative.iam.laundryin.views.paket.PaketModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesananActivity extends AppCompatActivity {

    ImageView ivDetailIcon;
    TextView tvDetailId,
            tvDetailStatus,
            tvDetailAddress,
            tvDetailDone,
            tvDetailPaketEstimate,
            tvDetailPaketName,
            tvDetailPrice,
            tvDetailIsPaid;
    Button btnDetailPesanan;
    NiceSpinner spDetailPesanan;

    BottomSheetDialog sheetDialog;

    String paketId;

    int selectedId = -1;
    boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        initToolbar();
        initData();
        loadData();
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

    public void loadData() {
        String id = getIntent().getStringExtra("orderId");

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getOrder(id).enqueue(new Callback<BaseDao<GetOrderResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<GetOrderResponseDao>> call, Response<BaseDao<GetOrderResponseDao>> response) {
                if (response.body().getCode() == 1) {
                    GetOrderResponseDao data = response.body().getData();
                    tvDetailId.setText("No. Pesanan : "+data.getId_order());
                    tvDetailStatus.setText(Tools.loadStatus(Integer.valueOf(data.getStatus())));
                    switch (Integer.valueOf(data.getStatus())){
                        case 0 :
                            ivDetailIcon.setImageResource(R.drawable.ic_van);
                            tvDetailIsPaid.setText("Belum Dibayar");
                            btnDetailPesanan.setVisibility(View.GONE);
                            break;
                        case 1 :
                            ivDetailIcon.setImageResource(R.drawable.ic_wash);
                            tvDetailIsPaid.setText("Belum Dibayar");
                            btnDetailPesanan.setVisibility(View.GONE);
                            break;
                        case 2 :
                            ivDetailIcon.setImageResource(R.drawable.ic_van);
                            tvDetailIsPaid.setText("Belum Dibayar");
                            btnDetailPesanan.setVisibility(View.VISIBLE);
                            btnDetailPesanan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            break;
                        case 3 :
                            ivDetailIcon.setImageResource(R.drawable.ic_van);
                            tvDetailIsPaid.setText("Sudah Dibayar");
                            btnDetailPesanan.setVisibility(View.VISIBLE);
                            btnDetailPesanan.setText("Ulas Paket");
                            btnDetailPesanan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showSheet();
                                }
                            });
                            break;
                        default:
                            ivDetailIcon.setImageResource(R.drawable.ic_van);
                            tvDetailIsPaid.setText("Belum Dibayar");
                            btnDetailPesanan.setVisibility(View.GONE);
                            break;
                    }
                    tvDetailAddress.setText(data.getAddress());

                    tvDetailPaketName.setText(data.getNama_paket());
                    tvDetailPaketEstimate.setText(data.getEstimasi_paket() +" Hari");

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, Integer.valueOf(data.getEstimasi_paket()));
                    Date todate1 = cal.getTime();
                    String fromdate = dateFormat.format(todate1);

                    tvDetailDone.setText(fromdate);

                    tvDetailPrice.setText(data.getTotal_bayar());
                    paketId = data.getId_paket();

                    if (isAdmin){
                        final String orderId = data.getId_order();
                        btnDetailPesanan.setVisibility(View.VISIBLE);
                        btnDetailPesanan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updateStatus(orderId);
                            }
                        });
                    }
                } else {
                    Toast.makeText(DetailPesananActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<GetOrderResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: " + t.getMessage());
                Toast.makeText(DetailPesananActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateStatus(String orderId){
        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.updateStatusOrder(orderId,String.valueOf(selectedId)).enqueue(new Callback<BaseDao<UpdateStatusOrderResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<UpdateStatusOrderResponseDao>> call, Response<BaseDao<UpdateStatusOrderResponseDao>> response) {
                if(response.body().getCode() == 1){
                    Toast.makeText(getApplicationContext(), "Berhasil Mengubah Status", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Gagal Mengubah Status", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<UpdateStatusOrderResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        ivDetailIcon = findViewById(R.id.ivDetailIcon);
        tvDetailId = findViewById(R.id.tvDetailId);
        tvDetailStatus = findViewById(R.id.tvDetailStatus);
        tvDetailAddress = findViewById(R.id.tvDetailAddress);
        tvDetailDone = findViewById(R.id.tvDetailDone);
        tvDetailPaketEstimate = findViewById(R.id.tvDetailPaketEstimate);
        tvDetailPaketName = findViewById(R.id.tvDetailPaketName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailIsPaid = findViewById(R.id.tvDetailIsPaid);
        btnDetailPesanan = findViewById(R.id.btnDetailPesanan);
        spDetailPesanan = findViewById(R.id.spDetailPesanan);

        List<String> listStatus = new ArrayList<>();
        listStatus.add("Menunggu Diambil");
        listStatus.add("Pakaian Dicuci");
        listStatus.add("Pakaian Dikirim");
        listStatus.add("Pesanan Selesai");
        listStatus.add("Pesanan Gagal");

        Tools.buildSpinner(
                this,
                spDetailPesanan,
                listStatus,
                new OnSpinnerItemSelectedListener() {
                    @Override
                    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                        if (position > 3){
                            selectedId = -1;
                        }else {
                            selectedId = position;
                        }
                    }
                },
                0
        );

        PreferencesUtils sp = new PreferencesUtils(this.getApplicationContext());
        String username = sp.get("uname", Constant.SAVED_USERNAME);
        if(!username.equalsIgnoreCase("admin")){
            spDetailPesanan.setVisibility(View.GONE);
        }else {
            spDetailPesanan.setVisibility(View.VISIBLE);
        }

        isAdmin = username.equalsIgnoreCase("admin");
    }


    public void showSheet(){
        sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setContentView(getLayoutInflater().inflate(R.layout.sheet_form,null));
        sheetDialog.show();

        TextView tvSheetTitle = sheetDialog.findViewById(R.id.tvSheetTitle);
        TextView tvSheetSkip = sheetDialog.findViewById(R.id.tvSheetSkip);
        final EditText etSheet = sheetDialog.findViewById(R.id.etSheet);
        Button btnSheet = sheetDialog.findViewById(R.id.btnSheet);

        tvSheetSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSheet();
            }
        });

        btnSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ulasPaket(etSheet.getText().toString());
            }
        });
    }

    public void hideSheet(){
        sheetDialog.dismiss();
    }

    public void ulasPaket(String ulasan){
        String id = getIntent().getStringExtra("orderId");
        PreferencesUtils sp = new PreferencesUtils(getApplicationContext());
        String username = sp.get("uname", Constant.SAVED_USERNAME);

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.doUlasan(
                paketId,
                username,
                ulasan
        ).enqueue(new Callback<BaseDao<DoUlasanResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<DoUlasanResponseDao>> call, Response<BaseDao<DoUlasanResponseDao>> response) {
                if (response.body().getCode() == 1) {
                    Toast.makeText(DetailPesananActivity.this,"Berhasil Menambahkan Ulasan",Toast.LENGTH_SHORT).show();
                    sheetDialog.dismiss();
                } else {
                    Toast.makeText(DetailPesananActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<DoUlasanResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: " + t.getMessage());
                Toast.makeText(DetailPesananActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
