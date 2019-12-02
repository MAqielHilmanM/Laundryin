package com.creative.iam.laundryin.views.order.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetOrderResponseDao;
import com.creative.iam.laundryin.tools.Constant;

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
            tvDetailWeight,
            tvDetailTotal,
            tvDetailPrice,
            tvDetailIsPaid;
    Button btnDetailPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        initData();
        loadData();
    }

    public void loadData() {
        String id = getIntent().getStringExtra("orderId");

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getOrder(id).enqueue(new Callback<BaseDao<GetOrderResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<GetOrderResponseDao>> call, Response<BaseDao<GetOrderResponseDao>> response) {
                if (response.body().getCode() == 1) {
                    
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

    public void initData() {
        ivDetailIcon = findViewById(R.id.ivDetailIcon);
        tvDetailId = findViewById(R.id.tvDetailId);
        tvDetailStatus = findViewById(R.id.tvDetailStatus);
        tvDetailAddress = findViewById(R.id.tvDetailAddress);
        tvDetailDone = findViewById(R.id.tvDetailDone);
        tvDetailPaketEstimate = findViewById(R.id.tvDetailPaketEstimate);
        tvDetailPaketName = findViewById(R.id.tvDetailPaketName);
        tvDetailWeight = findViewById(R.id.tvDetailWeight);
        tvDetailTotal = findViewById(R.id.tvDetailTotal);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailIsPaid = findViewById(R.id.tvDetailIsPaid);
        btnDetailPesanan = findViewById(R.id.btnDetailPesanan);


    }
}
