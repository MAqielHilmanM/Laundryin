package com.creative.iam.laundryin.views.main.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetAllOrderResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.views.main.home.HomeAdapter;
import com.creative.iam.laundryin.views.main.home.HomeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSubFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile_sub, container, false);

        initRecycler(v);
        if(getArguments() !=null){
            loadData();
        }

        return v;
    }

    List<ProfileSubModel> lists = new ArrayList<>();
    RecyclerView recyclerView;
    public void initRecycler(View v){
        recyclerView = v.findViewById(R.id.rvSubProfile);
        recyclerView.setAdapter(new ProfileSubAdapter(lists));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadData() {
        final boolean isHistory = getArguments().getBoolean("type", false);
        PreferencesUtils sp = new PreferencesUtils(getActivity().getApplicationContext());
        String username = sp.get("uname", Constant.SAVED_USERNAME);

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getAllOrder(username).enqueue(new Callback<BaseDao<List<GetAllOrderResponseDao>>>() {
            @Override
            public void onResponse(Call<BaseDao<List<GetAllOrderResponseDao>>> call, Response<BaseDao<List<GetAllOrderResponseDao>>> response) {
                if(response.body().getCode() == 1){
                    lists.clear();
                    for (GetAllOrderResponseDao order: response.body().getData()
                         ) {
                        if(isHistory && (order.getStatus().equals("3") || order.getStatus().equals("-1"))){
                            lists.add(new ProfileSubModel(
                                    order.getId_order(),
                                    Integer.valueOf(order.getStatus())
                            ));
                        }else if (!order.getStatus().equals("3") || !order.getStatus().equals("-1")){
                            lists.add(new ProfileSubModel(
                                    order.getId_order(),
                                    Integer.valueOf(order.getStatus())
                            ));
                        }
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<List<GetAllOrderResponseDao>>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(getActivity().getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
