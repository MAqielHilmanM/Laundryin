package com.creative.iam.laundryin.views.main.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.notification.NotifikasiActivity;
import com.creative.iam.laundryin.views.paket.PaketActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeModel> lists = new ArrayList<>();
    private Context context;

    public HomeAdapter(List<HomeModel> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeModel model = lists.get(i);
        viewHolder.initData(model);
        viewHolder.initListener(context,model);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemHome;
        private ImageView ivItemHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemHome = itemView.findViewById(R.id.tvItemHome);
            ivItemHome = itemView.findViewById(R.id.ivItemHome);
        }

        public void initData(HomeModel model){
            tvItemHome.setText(model.getTitle());
            Tools.loadImage(ivItemHome,model.getPicture());
        }

        public void initListener(final Context context, HomeModel model){
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PaketActivity.class);
                            context.startActivity(intent);
                        }
                    }
            );
        }
    }
}

