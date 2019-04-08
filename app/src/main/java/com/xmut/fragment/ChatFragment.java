package com.xmut.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.activity.R;
import com.xmut.adapter.MsgAdapter;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.Chat;
import com.xmut.hotel.Msg;
import com.xmut.hotel.Room;
import com.xmut.hotel.User;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ChatFragment extends Fragment {

    private View view;
    private List<Msg> msgList = new ArrayList<>();

    private static int length;
    private static final int UPDATE_TEXT = 1;
    private static String friendFlag = "1";

    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private SharedPreferences preferences;

    private OkHttpConnection connection;
    private LinearLayoutManager layoutManager;
    private JSONObject userJson;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.chat_layout, group, false);

        connection = new OkHttpConnection();
        preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        userJson = getUser();

        friendFlag = (String) userJson.get("friendId");


        inputText = view.findViewById(R.id.input_text);
        send = view.findViewById(R.id.send_btn);
        msgRecyclerView = view.findViewById(R.id.chat_recycle);
        layoutManager = new LinearLayoutManager(getContext());

        initRecycleView(msgList);
        MyTask myTask = new MyTask();
        myTask.execute();
        //refreshMsg();
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();

                //showResponse(msgList);
                //initMsg(userJson);
                adapter.notifyDataSetChanged();
                if(!"".equals(content)){
                    userJson.put("friendId", preferences.getString("friendId", ""));
                    userJson.put("userId", userJson.get("userId"));

                    userJson.put("content", content);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connection.sendMassage(userJson, "send");
                        }
                    }).start();

                    Msg msg = new Msg();
                    msg.setContent(content);
                    msg.setType(Msg.TYPE_SENT);
                    msgList.add(msg);
                    initRecycleView(msgList);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
        return  view;
    }

    private void initRecycleView(List<Msg> list){
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(list);
        msgRecyclerView.setAdapter(adapter);
    }

    private void initMsg(final JSONObject json){
        new Thread(new Runnable() {
            @Override
            public void run() {
                connection = new OkHttpConnection();
                msgList = new ArrayList<>();
                String response = connection.getMassage(userJson, "getContent");
                if (response != null) {
                    msgList = JSONArray.parseArray(response, Msg.class);
                }
                length = msgList.size();
                showResponse(msgList);
                onCreate(null);
            }
        }).start();

    }
    private void showResponse(final List<Msg> list){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleView(list);
            }
        });
    }

    private JSONObject getUser(){
        preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        JSONObject json = new JSONObject();
        json.put("userId" ,preferences.getString("userId",""));
        json.put("userName" ,preferences.getString("userName", ""));
        json.put("sex" ,preferences.getString("sex", ""));
        json.put("friendId", preferences.getString("friendId", ""));
        return json;
    }

    //Async异步传输
    private class MyTask extends AsyncTask<String, List<Msg>, String>{

        @Override
        protected void onPreExecute() {
            initMsg(userJson);
        }

        @Override
        protected String doInBackground(String... strings) {

            connection = new OkHttpConnection();
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("test", friendFlag);
                msgList = new ArrayList<>();
                String response = connection.getMassage(userJson, "getContent");
                if (response != null) {
                    msgList = JSONArray.parseArray(response, Msg.class);
                }
                userJson.put("friendId", preferences.getString("friendId",""));

                publishProgress(msgList);
            }
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度

        @Override
        protected void onProgressUpdate(List<Msg>... values) {
            //Log.i("test", "fried:" + friendFlag);

            if(msgList.size()!=length || !friendFlag.equals(userJson.getString("friendId"))){
                showResponse(msgList);
                onCreate(null);
                length = msgList.size();
                friendFlag = (String) userJson.get("friendId");
            }

        }
    }
}
