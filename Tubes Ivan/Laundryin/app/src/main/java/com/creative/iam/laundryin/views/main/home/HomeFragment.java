package com.creative.iam.laundryin.views.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;
import com.creative.iam.laundryin.views.paket.PaketModel;
import com.creative.iam.laundryin.views.paket.create.CreatePacketActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    TextView tvHomeTitle;
    FloatingActionButton fab;

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

    public void initListener(){
        tvHomeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotifikasiActivity.class);
                getActivity().startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePacketActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void initData(View v){
        fab = v.findViewById(R.id.fabHome);
        tvHomeTitle = v.findViewById(R.id.tvHomeTitle);

        PreferencesUtils sp = new PreferencesUtils(getActivity().getApplicationContext());
        String username = sp.get("uname", Constant.SAVED_USERNAME);
        if(!username.equalsIgnoreCase("admin")){
            hideFAB();
        }else {
            unHideFAB();
        }
    }

    public void hideFAB(){
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setBehavior(null); //should disable default animations
        p.setAnchorId(View.NO_ID); //should let you set visibility
        fab.setLayoutParams(p);
        fab.setVisibility(View.GONE); // View.INVISIBLE might also be worth trying
    }

    public void unHideFAB(){
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setBehavior(new FloatingActionButton.Behavior());
        p.setAnchorId(R.id.anchor);
        p.anchorGravity = Gravity.BOTTOM | GravityCompat.END;
        fab.setLayoutParams(p);
        fab.setVisibility(View.VISIBLE);
    }
}
