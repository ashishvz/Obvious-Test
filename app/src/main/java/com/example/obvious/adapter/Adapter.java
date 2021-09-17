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
import com.example.obvious.interfaces.onClickListener;
import com.example.obvious.model.DataModel;
import com.example.obvious.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final Context context;
    private final List<DataModel> dataModels;
    private final onClickListener onClickListener;

    public Adapter(Context context, List<DataModel> dataModels, onClickListener onClickListener) {
        this.context = context;
        this.dataModels = dataModels;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_image, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel model = dataModels.get(holder.getAdapterPosition());
        Glide.with(context).load(model.url).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.getAdapterPosition(), holder.imageView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
