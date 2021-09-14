package com.example.obvious.utils;

import android.annotation.SuppressLint;

import com.example.obvious.model.DataModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ToolKit {

    public static List<DataModel> sortArray(List<DataModel> dataModels) {
        Collections.sort(dataModels, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel model1, DataModel model2) {
                return model1.getDate().compareTo(model1.getDate());
            }
        });
        return dataModels;
    }
}
