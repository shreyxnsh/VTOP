package com.shreyxnsh.vtop.ui.notice;

import android.app.Activity;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shreyxnsh.vtop.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NoticeData {
    String Title, image, date, time, key;

    public NoticeData() {
    }

    public NoticeData(String name, String designation, String email, String cabin, String title, String downloadUrl) {
    }

    public NoticeData(String title, String image, String date, String time, String key) {
        Title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
    }




    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }




}
