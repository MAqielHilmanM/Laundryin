package com.creative.iam.laundryin.views.onboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardFragment extends Fragment {


    public OnboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onboard, container, false);

        TextView tvOnboardTitle = v.findViewById(R.id.tvOnboardTitle);
        TextView tvOnboardSubtitle = v.findViewById(R.id.tvOnboardSubtitle);
        ImageView ivOnboardImage = v.findViewById(R.id.ivOnboardImage);

        int status = getArguments().getInt("status",-1);
        if(status != -1){
            switch (status){
                case 2 :
                    tvOnboardTitle.setText("Pengerjaan Cepat");
                    tvOnboardSubtitle.setText("Pengerjaan laundry yang cepat karena pegawai laundryin yang handal");
                    ivOnboardImage.setImageResource(R.drawable.img_onboard2);
                    break;
                case 3 :
                    tvOnboardTitle.setText("Harga Bersahabat");
                    tvOnboardSubtitle.setText("Menyediakan harga laundry yang jauh lebih murah dan praktis");
                    ivOnboardImage.setImageResource(R.drawable.img_onboard3);
                    break;
                default:
                    tvOnboardTitle.setText("Antar jemput cucian");
                    tvOnboardSubtitle.setText("Melayani antar jemput cucian daerah sekitar kampus Telkom University");
                    ivOnboardImage.setImageResource(R.drawable.img_onboard1);
                    break;
            }
        }

        return v;
    }

}
