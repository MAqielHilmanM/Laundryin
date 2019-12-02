package com.creative.iam.laundryin.views.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetAllPacketResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;
import com.creative.iam.laundryin.views.paket.PaketModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    TextView tvHomeTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        initData(view);
        initListener();
        initRecycler(view);
        loadData();
        return view;
    }


    private void loadData() {
        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getAllPacket().enqueue(
                new Callback<BaseDao<List<GetAllPacketResponseDao>>>() {
                    @Override
                    public void onResponse(Call<BaseDao<List<GetAllPacketResponseDao>>> call, Response<BaseDao<List<GetAllPacketResponseDao>>> response) {
                        if(response.body().getCode() == 1){
                            lists.clear();
                            for (GetAllPacketResponseDao packet: response.body().getData()
                            ) {
                                lists.add(
                                        new HomeModel(
                                                packet.getId_paket(),
                                                packet.getNama_paket(),
                                                packet.getUrl_picture()
                                        )
                                );
                            }
                            recyclerView.getAdapter().notifyDataSetChanged();

                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Failed To Get Packets", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseDao<List<GetAllPacketResponseDao>>> call, Throwable t) {
                        Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                        Toast.makeText(getActivity().getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    List<HomeModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(View v){
        recyclerView = v.findViewById(R.id.rvHome);
        recyclerView.setAdapter(new HomeAdapter(lists));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    public void loadDummy(){
        lists.add(new HomeModel("","Paket Hemat",""));
        lists.add(new HomeModel("","Kiloan",""));
        lists.add(new HomeModel("","Paket 4 Hari",""));
        lists.add(new HomeModel("","Paket Kilat",""));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void initListener(){
        tvHomeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotifikasiActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void initData(View v){
        tvHomeTitle = v.findViewById(R.id.tvHomeTitle);
    }
}
