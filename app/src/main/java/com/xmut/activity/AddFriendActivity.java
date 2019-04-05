package com.xmut.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activity.R;
import com.xmut.hotel.User;

public class AddFriendActivity extends AppCompatActivity {

    private Button add_btn;
    private TextView add_text;
    private SharedPreferences preferences;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        add_btn = findViewById(R.id.add_btn);
        add_text = findViewById(R.id.add_text);

        user = new User();
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        Log.i("fra", "preferences " + preferences);
        user.setUserId(preferences.getString("userId",""));
        user.setUserName(preferences.getString("userName", ""));
        user.setUserSex(preferences.getString("sex", ""));
        add_text.setText(user.getUserId() + user.getUserName() + user.getUserSex());
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
