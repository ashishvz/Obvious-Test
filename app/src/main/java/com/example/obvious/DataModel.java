package com.example.obvious;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class DataModel implements Serializable {

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("date")
    public Date date;

    @SerializedName("explanation")
    public String explanation;

    @SerializedName("hdurl")
    public String hdUrl;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    public int imageColor;

    public DataModel(String copyright, Date date, String explanation, String hdUrl, String title, String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdUrl = hdUrl;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageColor() {
        return imageColor;
    }

    public void setImageColor(int imageColor) {
        this.imageColor = imageColor;
    }
}
