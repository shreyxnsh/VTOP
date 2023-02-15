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
    Context mContext;
    Activity mActivity;
    String userFcmToken;

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

    public NoticeData(String userFcmToken, String title, String body, Context mContext, Activity mActivity) {
        this.userFcmToken = userFcmToken;
        Title = title;
        this.mContext = mContext;
        this.mActivity = mActivity;
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

    private RequestQueue requestQueue;
    private final String postUrl = "https://fcm.googleapis.com/fcm/send";
    private final String fcmServerKey ="AAAAFiVI5j8:APA91bEnFehdjH_EaSOiB4XxNlbo-_pLVgcKuFYxKtbyzKXWs1Mgd3_zQMCrewm3JRAd4-FnhxGXGynSNiJz4bHkTyAyhTlnJhF3Iwv6MIAnuk_ll583k3SRk7_8ONoBD6HYIiz-Wgec";

    public void SendNotifications() {

        requestQueue = Volley.newRequestQueue(mActivity);
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("to", userFcmToken);
            JSONObject notiObject = new JSONObject();
            notiObject.put("title", "Notice Alert!");
            notiObject.put("body", Title);
            notiObject.put("icon", R.drawable.vtopdarkblue); // enter icon that exists in drawable only



            mainObj.put("notification", notiObject);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, postUrl, mainObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    // code run is got response

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // code run is got error

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {


                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=" + fcmServerKey);
                    return header;


                }
            };
            requestQueue.add(request);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
