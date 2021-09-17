package com.example.obvious.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.obvious.interfaces.onBackClick;
import com.example.obvious.model.DataModel;
import com.example.obvious.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {

    private final Context context;
    private final List<DataModel> dataModels;
    private final onBackClick onBackClick;

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
        Glide.with(context).load(model.hdUrl).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.imageView);
        holder.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBackClick != null)
                    onBackClick.onClicked();
            }
        });
        Glide.with(context).asBitmap().load(model.url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Palette.from(resource).generate(palette -> {
                    assert palette != null;
                    holder.imageBackgroundCard.setCardBackgroundColor(palette.getDominantColor(context.getResources().getColor(R.color.purple_200)));
                });
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final ImageView backBtn;
        private final MaterialTextView title;
        private final MaterialTextView description;
        private final MaterialTextView date;
        private final MaterialCardView imageBackgroundCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleText);
            description = itemView.findViewById(R.id.descText);
            date = itemView.findViewById(R.id.dateText);
            imageBackgroundCard = itemView.findViewById(R.id.imageBackgroundCard);
            backBtn = itemView.findViewById(R.id.backBtn);
        }
    }
}
