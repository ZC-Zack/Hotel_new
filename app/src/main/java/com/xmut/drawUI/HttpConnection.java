package com.xmut.drawUI;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    private URL url;
    private HttpURLConnection connection;
    private BufferedReader reader;
    private StringBuilder response;
    private InputStream in;

    private String urlController = "http://192.168.43.203:8080/";

    private static final String TGL="test";
    public String getTable(String data){
        try {
            url = new URL(urlController + data);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            Log.i(TGL,"connection "+url);
            in = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            response = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                response.append(line);
            }
        }catch (Exception e){
            Log.i(TGL,e.toString());
            e.printStackTrace();
        }

        return response.toString();
    }
}
