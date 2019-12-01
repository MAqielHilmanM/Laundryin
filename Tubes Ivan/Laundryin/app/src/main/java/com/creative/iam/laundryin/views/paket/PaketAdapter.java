package com.creative.iam.laundryin.views.paket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.main.home.HomeModel;
import com.joooonho.SelectableRoundedImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.ViewHolder> {
    private List<PaketModel> lists = new ArrayList<>();
    private Context context;

    public PaketAdapter(List<PaketModel> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_paket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PaketModel model = lists.get(i);
        viewHolder.initData(model);
        viewHolder.initListener(context,model);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SelectableRoundedImageView srivItemReview;
        TextView tvItemReviewName;
        TextView tvItemReviewDesc;
        TextView tvItemReviewDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            srivItemReview = itemView.findViewById(R.id.srivItemReview);
            tvItemReviewName = itemView.findViewById(R.id.tvItemReviewName);
            tvItemReviewDesc = itemView.findViewById(R.id.tvItemReviewDesc);
            tvItemReviewDate = itemView.findViewById(R.id.tvItemReviewDate);
        }

        public void initData(PaketModel model){
            tvItemReviewName.setText(model.getName());
            tvItemReviewDesc.setText(model.getReview());
            tvItemReviewDate.setText(model.getDate());
            Tools.loadImage(srivItemReview,model.getPic());
        }

        public void initListener(Context context, PaketModel model){
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

