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

public class MainActivity extends AppCompatActivity implements Adapter.onClickListener {

    private ContentLoadingProgressBar contentLoadingProgressBar;
    private RecyclerView recyclerView;
    private List<DataModel> modelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Adapter.onClickListener onClickListener = this;
        setContentView(R.layout.activity_main);
        contentLoadingProgressBar = findViewById(R.id.contentLoadingBar);
        recyclerView = findViewById(R.id.imageRecycler);
        modelData = new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
        contentLoadingProgressBar.show();
        Adapter adapter = new Adapter(MainActivity.this, parseDataFromJSON(), onClickListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<DataModel> parseDataFromJSON() {
        try {
            JSONArray dataArray = new JSONArray(getJSONFromAssets());
            for (int i = 0; i < dataArray.length(); i++)
                modelData.add(new Gson().fromJson(dataArray.get(i).toString(), DataModel.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setVisibility(View.VISIBLE);
        contentLoadingProgressBar.hide();
        return ToolKit.sortArray(modelData);
    }

    private String getJSONFromAssets() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open(AppConstants.FILE_NAME);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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