package com.xmut.drawUI;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpConnection {
    private String url = "http://118.24.221.92:8080/";
    private OkHttpClient client;
    private Request request;
    private String responseData;
    private Response response;

    public String getData(String data){

        client = new OkHttpClient();
        request = new Request.Builder().url(url + data).build();
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
    }
}
