package com.example.obvious.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.obvious.model.DataModel;
import com.example.obvious.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    private Context context;
    private List<DataModel> dataModels;
    private onBackClick onBackClick;

    public DetailAdapter(Context context, List<DataModel> dataModels, onBackClick onBackClick) {
        this.context = context;
        this.dataModels = dataModels;
        this.onBackClick = onBackClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_detail_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel model = dataModels.get(holder.getAdapterPosition());
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getExplanation());
        holder.date.setText(model.getDate());
        holder.imageBackgroundCard.setCardBackgroundColor(model.getImageColor());
        Glide.with(context).load(model.hdUrl).into(holder.imageView);
        holder.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBackClick != null)
                    onBackClick.onClicked();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, backBtn;
        private MaterialTextView title, description, date;
        private MaterialCardView imageBackgroundCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailImageView);
            title = itemView.findViewById(R.id.titleText);
            description = itemView.findViewById(R.id.descText);
            date = itemView.findViewById(R.id.dateText);
            imageBackgroundCard = itemView.findViewById(R.id.imageBackgroundCard);
            backBtn = itemView.findViewById(R.id.backBtn);
        }
    }

    public interface onBackClick {
        void onClicked();
    }
}