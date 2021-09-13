package com.example.obvious;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    private Context context;
    private List<DataModel> dataModels;

    public DetailAdapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
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
        holder.date.setText(model.getDate().toString());
        holder.imageBackgroundCard.setCardBackgroundColor(model.getImageColor());
        Glide.with(context).load(model.hdUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private MaterialTextView title, description, date;
        private MaterialCardView imageBackgroundCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detailImageView);
            title = itemView.findViewById(R.id.titleText);
            description = itemView.findViewById(R.id.descText);
            date = itemView.findViewById(R.id.dateText);
            imageBackgroundCard = itemView.findViewById(R.id.imageBackgroundCard);
        }
    }
}
