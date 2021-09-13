package com.example.obvious.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.obvious.R;
import com.example.obvious.adapter.Adapter;
import com.example.obvious.model.DataModel;
import com.example.obvious.utils.ToolKit;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.onClickListener {

    private ContentLoadingProgressBar contentLoadingProgressBar;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Adapter.onClickListener onClickListener;
    private List<DataModel> modelData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onClickListener = this;
        setContentView(R.layout.activity_main);
        contentLoadingProgressBar = findViewById(R.id.contentLoadingBar);
        recyclerView = findViewById(R.id.imageRecycler);
        modelData = new ArrayList<>();
        recyclerView.setVisibility(View.GONE);
        contentLoadingProgressBar.show();
        adapter = new Adapter(MainActivity.this, parseDataFromJSON(), onClickListener);
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
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataList", (Serializable) modelData);
        intent.putExtra("Bundle", bundle);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}