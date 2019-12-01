package com.creative.iam.laundryin.views.main.profile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.order.detail.DetailPesananActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileSubAdapter extends RecyclerView.Adapter<ProfileSubAdapter.ViewHolder> {
    List<ProfileSubModel> lists = new ArrayList<>();
    Context context;

    public ProfileSubAdapter(List<ProfileSubModel> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile_sub,viewGroup));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.initData(lists.get(i));
        viewHolder.initListener(context,lists.get(i));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void initData(ProfileSubModel data){

        }

        public void initListener(final Context context, ProfileSubModel data){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, DetailPesananActivity.class));
                }
            });
        }
    }


}
