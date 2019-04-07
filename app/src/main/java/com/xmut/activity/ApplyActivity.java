package com.xmut.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.activity.R;
import com.xmut.adapter.ApplyAdapter;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.ApplyUser;
import com.xmut.hotel.Msg;
import com.xmut.hotel.Room;
import com.xmut.hotel.User;

import java.util.List;

public class ApplyActivity extends AppCompatActivity {

    private List<ApplyUser> list;
    private ApplyAdapter applyAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private OkHttpConnection okHttpConnection;
    private SharedPreferences preferences;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        recyclerView = findViewById(R.id.apply_recycle);

        MyTask myTask = new MyTask();
        myTask.execute();
        Button return_btn=(Button)findViewById(R.id.return_btn1);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ApplyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initRecycleView(List<ApplyUser> list){
        applyAdapter = new ApplyAdapter(list, preferences);
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setAdapter(applyAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void initApply(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = okHttpConnection.postAddPost(json,"getApply");
                list = JSONArray.parseArray(response, ApplyUser.class);
                showResponse(list);
                //onCreate(null);
            }
        }).start();
    }

    public void init(){
        okHttpConnection = new OkHttpConnection();
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        json = new JSONObject();
        String userId = preferences.getString("userId", "");
        String userName = preferences.getString("userName","");
        String sex = preferences.getString("sex", "");
        json.put("userId", userId);
        json.put("userName", userName);
        json.put("sex", sex);
    }

    private void showResponse(final List<ApplyUser> list){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleView(list);
                applyAdapter = new ApplyAdapter(list, preferences);
                recyclerView.setAdapter(applyAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        });
    }

    private class MyTask extends AsyncTask<String, List<ApplyUser>, String>{

        @Override
        protected void onPreExecute() {
            init();
            initApply();
        }

        @Override
        protected String doInBackground(String... strings) {
            okHttpConnection = new OkHttpConnection();
            while (true){
                String response = okHttpConnection.postAddPost(json,"getApply");
                list = JSONArray.parseArray(response, ApplyUser.class);
                applyAdapter = new ApplyAdapter(list, preferences);
                recyclerView.setAdapter(applyAdapter);
            }

        }
    }
}
