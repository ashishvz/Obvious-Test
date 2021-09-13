package com.example.obvious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ImageDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DetailAdapter adapter;
    private List<DataModel> dataModels;
    private SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        dataModels = (List<DataModel>) bundle.getSerializable("dataList");
        snapHelper = new LinearSnapHelper();
        recyclerView = findViewById(R.id.imageDetailRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ImageDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailAdapter(ImageDetailActivity.this, dataModels);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(getIntent().getIntExtra("position", 0));
    }
}