package com.xmut.drawUI;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

public class GetSharedPreferences extends AppCompatActivity {

    private JSONObject json;
    private SharedPreferences sharedPreferences;

    public GetSharedPreferences() {
        json = new JSONObject();
        //Log.i("tag", "GetSharedPreferences: " + sharedPreferences);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        //Log.i("tag", "GetSharedPreferences: " + sharedPreferences);
        String userId = sharedPreferences.getString("userId", "");
        String userName = sharedPreferences.getString("userName","");
        String sex = sharedPreferences.getString("sex", "");
        json.put("userId", userId);
        json.put("userName", userName);
        json.put("sex", sex);
    }

    public JSONObject getJson(){
        return json;
    }
}
