package com.xmut.drawUI;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpConnection {
    private String url = "http://118.24.221.92:8080/";
//    private String url = "http://110.80.48.196:8080/";
    private OkHttpClient client;
    private Request request;
    private String responseData;
    private Response response;
    private RequestBody requestBody;
    private SharedPreferences sharedPreferences;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OkHttpConnection(){
        client = new OkHttpClient();
    }

    public String getData(String data){

        request = new Request.Builder().url(url + data).build();
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("user", "data: "+ responseData);
        return responseData;
    }

    public String postAddPost(JSONObject json, String target){

        requestBody = RequestBody.create(JSON, String.valueOf(json));
//        requestBody = new FormBody.Builder().add(json.);
        request = new Request.Builder().url(url + target).post(requestBody).build();

        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            if(response.isSuccessful()){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public String postApplyResult(JSONObject json, String target){
        //sharedPreferences = getgetSharedPreferences("user", MODE_PRIVATE);
        //json.put("friendId", sharedPreferences.getString("userId", ""));
        requestBody = RequestBody.create(JSON, String.valueOf(json));
        request = new Request.Builder().url(url + target).post(requestBody).build();
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            if(response.isSuccessful()){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public void sendMassage(JSONObject json, String target){
        requestBody = RequestBody.create(JSON, String.valueOf(json));
        request = new Request.Builder().url(url + target).post(requestBody).build();
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            if(response.isSuccessful()){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
