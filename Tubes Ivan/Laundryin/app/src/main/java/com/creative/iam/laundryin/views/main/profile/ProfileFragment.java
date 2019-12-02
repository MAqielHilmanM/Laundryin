package com.creative.iam.laundryin.views.main.profile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;

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
        pager.addFragment(new ProfileSubFragment(), "Pesanan Laundry");
        pager.addFragment(new ProfileSubFragment(), "History Transaksi");
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initListener(View v) {
        btnProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
