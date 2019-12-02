package com.creative.iam.laundryin.views.main.profile;

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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile_sub,viewGroup,false));
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
        ImageView ivSubProfileIcon;
        TextView tvSubProfileOrder,tvSubProfileProcess;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSubProfileIcon = itemView.findViewById(R.id.ivSubProfileIcon);
            tvSubProfileOrder = itemView.findViewById(R.id.tvSubProfileOrder);
            tvSubProfileProcess = itemView.findViewById(R.id.tvSubProfileProcess);
        }

        public void initData(ProfileSubModel data){
            tvSubProfileOrder.setText("No. Pesanan : "+data.getId());
            tvSubProfileProcess.setText(Tools.loadStatus(data.getStatus()));
            switch (data.getStatus()){
                case 0 :
                    ivSubProfileIcon.setImageResource(R.drawable.ic_van);
                    break;
                case 1 :
                    ivSubProfileIcon.setImageResource(R.drawable.ic_wash);
                    break;
                case 2 :
                    ivSubProfileIcon.setImageResource(R.drawable.ic_van);
                    break;
                case 3 :
                    ivSubProfileIcon.setImageResource(R.drawable.ic_van);
                    break;
                default:
                    ivSubProfileIcon.setImageResource(R.drawable.ic_van);
                    break;
            }
        }

        public void initListener(final Context context, final ProfileSubModel data){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailPesananActivity.class);
                    intent.putExtra("orderId", data.getId());
                    context.startActivity(intent);
                }
            });
        }
    }


}
