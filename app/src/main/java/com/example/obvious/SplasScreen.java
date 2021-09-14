package com.example.obvious;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.obvious.activity.MainActivity;
import com.google.android.material.button.MaterialButton;

public class SplasScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);
        ImageView imageView = findViewById(R.id.splashImageView);
        MaterialButton button = findViewById(R.id.button);
        Glide.with(getApplicationContext()).asGif().load(R.drawable.space_dance).into(new SimpleTarget<GifDrawable>() {
            @Override
            public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                resource.start();
                imageView.setImageDrawable(resource);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplasScreen.this, MainActivity.class));
                finish();
            }
        });
    }
}