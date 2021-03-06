package com.xmut.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.activity.R;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.User;

public class AddFriendActivity extends AppCompatActivity {

    private Button add_find;
    private TextView add_text;
    private SharedPreferences preferences;
    private User user;
    private OkHttpConnection okHttpConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        add_find = findViewById(R.id.find);
        add_text = findViewById(R.id.add_name);
        okHttpConnection = new OkHttpConnection();
        user = new User();
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        user.setUserId(preferences.getString("userId",""));
        user.setUserName(preferences.getString("userName", ""));
        user.setSex(preferences.getString("sex", ""));
        add_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendId = add_text.getText().toString();
                final JSONObject json = new JSONObject();
                json.put("friendId", friendId);
                json.put("userId", user.getUserId());
                Log.i("post" , "json " + json.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        okHttpConnection.postAddPost(json, "apply");
                    }
                }).start();
                Toast.makeText(AddFriendActivity.this, "已发送申请", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
