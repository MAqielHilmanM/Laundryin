package com.creative.iam.laundryin.views.main.profile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.GetProfileResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;
import com.creative.iam.laundryin.views.order.confirm.ConfirmPesananActivity;
import com.creative.iam.laundryin.views.order.success.PesanBerhasilActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    Button btnProfileLogout;
    TextView tvProfileTitle, tvProfileAddress;
    ImageView ivProfilePictures;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        initData(v);
        initListener(v);
        loadProfile();

        return v;
    }

    private void initData(View v) {
        btnProfileLogout = v.findViewById(R.id.btnProfileLogout);
        tvProfileTitle = v.findViewById(R.id.tvProfileTitle);
        tvProfileAddress = v.findViewById(R.id.tvProfileAddress);
        ivProfilePictures = v.findViewById(R.id.ivProfilePictures);
        viewPager = v.findViewById(R.id.pager);
        tabLayout = v.findViewById(R.id.tablayout);
        ProfilePager pager = new ProfilePager(getChildFragmentManager());

        ProfileSubFragment profileSubFragment = new ProfileSubFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isHistory",false);
        profileSubFragment.setArguments(bundle);
        pager.addFragment(profileSubFragment, "Pesanan Laundry");

        ProfileSubFragment2 profileSubFragment2 = new ProfileSubFragment2();
        pager.addFragment(profileSubFragment2, "History Transaksi");

        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

        ivProfilePictures.setImageResource(R.drawable.basil);
    }

    private void loadProfile(){
        PreferencesUtils sp = new PreferencesUtils(getActivity().getApplicationContext());
        String username = sp.get("uname", Constant.SAVED_USERNAME);

        ApiInterface apiInterface = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiInterface.getProfile(
            username
        ).enqueue(new Callback<BaseDao<GetProfileResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<GetProfileResponseDao>> call, Response<BaseDao<GetProfileResponseDao>> response) {
                if(response.body().getCode() == 1){
                    tvProfileTitle.setText(response.body().getData().getNama());
                    tvProfileAddress.setText(response.body().getData().getAlamat());
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BaseDao<GetProfileResponseDao>> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "onFailure: "+t.getMessage() );
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener(View v) {
        btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                PreferencesUtils sp = new PreferencesUtils(getActivity().getApplicationContext());
                sp.clear();
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
