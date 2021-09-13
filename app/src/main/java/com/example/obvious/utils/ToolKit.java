package com.example.obvious.utils;

import android.annotation.SuppressLint;

import com.example.obvious.model.DataModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ToolKit {

    public static List<DataModel> sortArray(List<DataModel> dataModels) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collections.sort(dataModels, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel model1, DataModel model2) {
                try {
                    return simpleDateFormat.parse(model1.getDate()).compareTo(simpleDateFormat.parse(model1.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return dataModels;
    }
}
