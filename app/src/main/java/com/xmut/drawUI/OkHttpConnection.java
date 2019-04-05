package com.xmut.drawUI;

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
    private OkHttpClient client;
    private Request request;
    private String responseData;
    private Response response;
    private RequestBody requestBody;

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

        return responseData;
    }

    public void postAddPost(JSONObject json, String target){

        requestBody = RequestBody.create(JSON, String.valueOf(json));
//        requestBody = new FormBody.Builder().add(json.);
        request = new Request.Builder().url(url + target).post(requestBody).build();

        try {
            response = client.newCall(request).execute();
            if(response.isSuccessful()){
                Log.i("post", response.body().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
