package com.example.obvious.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.example.obvious.R;
import com.example.obvious.adapter.Adapter;
import com.example.obvious.constants.AppConstants;
import com.example.obvious.dataProvider.DataProvider;
import com.example.obvious.interfaces.onClickListener;
import com.example.obvious.model.DataModel;
import com.example.obvious.utils.ToolKit;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onClickListener {

    private RecyclerView recyclerView;
    private List<DataModel> modelData;
    private DataProvider dataProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onClickListener onClickListener = this;
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.imageRecycler);
        modelData = new ArrayList<>();
        dataProvider = new DataProvider(getApplicationContext(), modelData);
        Adapter adapter = new Adapter(MainActivity.this, dataProvider.parseDataFromJSON(), onClickListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(int position, ImageView imageView) {
        Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
        Pair<View, String> p1 = Pair.create(imageView, AppConstants.IMAGEVIEW);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, p1);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.DATA_LIST, (Serializable) modelData);
        intent.putExtra(AppConstants.BUNDLE, bundle);
        intent.putExtra(AppConstants.POSITION, position);
        startActivity(intent, activityOptions.toBundle());
    }
}