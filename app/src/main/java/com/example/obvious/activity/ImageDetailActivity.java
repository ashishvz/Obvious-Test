package com.example.obvious.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import com.example.obvious.R;
import com.example.obvious.adapter.DetailAdapter;
import com.example.obvious.constants.AppConstants;
import com.example.obvious.model.DataModel;

import java.util.List;

public class ImageDetailActivity extends AppCompatActivity implements DetailAdapter.onBackClick {

    private RecyclerView recyclerView;
    private DetailAdapter adapter;
    private List<DataModel> dataModels;
    private SnapHelper snapHelper;
    private DetailAdapter.onBackClick onBackClick;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Bundle bundle = getIntent().getBundleExtra(AppConstants.BUNDLE);
        dataModels = (List<DataModel>) bundle.getSerializable(AppConstants.DATA_LIST);
        onBackClick = this;
        snapHelper = new LinearSnapHelper();
        recyclerView = findViewById(R.id.imageDetailRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ImageDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailAdapter(ImageDetailActivity.this, dataModels, onBackClick);
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(getIntent().getIntExtra(AppConstants.POSITION, 0));
    }

    @Override
    public void onClicked() {
        onBackPressed();
    }
}