package com.creative.iam.laundryin.views.notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creative.iam.laundryin.R;

import java.util.ArrayList;
import java.util.List;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {
    private List<NotifikasiModel> lists = new ArrayList<>();
    private Context context;

    public NotifikasiAdapter(List<NotifikasiModel> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifikasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotifikasiModel model = lists.get(i);
        viewHolder.initData(model);
        viewHolder.initListener(context,model);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemNotifikasiMessage;
        TextView tvItemNotifikasiDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemNotifikasiMessage = itemView.findViewById(R.id.tvItemNotifikasiMessage);
            tvItemNotifikasiDate = itemView.findViewById(R.id.tvItemNotifikasiDate);
        }

        public void initData(NotifikasiModel model){
            tvItemNotifikasiMessage.setText(model.getMessage());
            tvItemNotifikasiDate.setText(model.getDate());
        }

        public void initListener(Context context, NotifikasiModel model){
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }
            );
        }
    }
}

