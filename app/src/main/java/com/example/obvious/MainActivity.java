package com.example.obvious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ContentLoadingProgressBar contentLoadingProgressBar;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentLoadingProgressBar = findViewById(R.id.contentLoadingBar);
        recyclerView = findViewById(R.id.imageRecycler);
        recyclerView.setVisibility(View.GONE);
        contentLoadingProgressBar.show();
        adapter = new Adapter(MainActivity.this, parseDataFromJSON());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<DataModel> parseDataFromJSON() {
        List<DataModel> modelData = new ArrayList<>();
        try {
            JSONArray dataArray = new JSONArray(getJSONFromAssets());
            for (int i = 0; i < dataArray.length(); i++)
                modelData.add(new Gson().fromJson(dataArray.get(i).toString(), DataModel.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setVisibility(View.VISIBLE);
        contentLoadingProgressBar.hide();

        return modelData;
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
}