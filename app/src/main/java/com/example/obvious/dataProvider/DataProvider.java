package com.example.obvious.dataProvider;

import android.content.Context;
import android.view.View;

import com.example.obvious.constants.AppConstants;
import com.example.obvious.model.DataModel;
import com.example.obvious.utils.ToolKit;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public Context context;
    public List<DataModel> modelData;
    public DataProvider(Context context, List<DataModel> modelData) {
        this.context = context;
        this.modelData = modelData;
    }

    public List<DataModel> parseDataFromJSON() {
        try {
            JSONArray dataArray = new JSONArray(getJSONFromAssets());
            for (int i = 0; i < dataArray.length(); i++)
                modelData.add(new Gson().fromJson(dataArray.get(i).toString(), DataModel.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ToolKit.sortArray(modelData);
    }

    public String getJSONFromAssets() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(AppConstants.FILE_NAME);
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
}
