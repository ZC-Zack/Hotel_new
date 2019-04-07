package com.xmut.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
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
    private List<Chat> chatList;

    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private SharedPreferences preferences;
    private User user;
    private OkHttpConnection connection;
    private LinearLayoutManager layoutManager;
    private JSONObject userJson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.chat_layout, group, false);
        connection = new OkHttpConnection();
        userJson = getUser();
        initMsg(userJson);
        inputText = view.findViewById(R.id.input_text);
        send = view.findViewById(R.id.send_btn);
        msgRecyclerView = view.findViewById(R.id.chat_recycle);
        layoutManager = new LinearLayoutManager(getContext());
        initRecycleView(msgList);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    final JSONObject json = new JSONObject();
                    json.put("userId", userJson.get("userId"));
                    Log.i("tof", "userId: " + userJson.get("userId"));
                    json.put("friendId", "1");
                    json.put("content", content);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connection.sendMassage(json, "send");
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
                String response = connection.getMassage(json, "getContent");
                if(response != null){
                    msgList = JSONArray.parseArray(response, Msg.class);
                    Log.i("tof", "msgList: " + msgList.toString() + "  "  + msgList.size());
                }

                showResponse(msgList);
                onCreate(null);
            }
        }).start();
//        Msg msg1 = new Msg();
//        msg1.setContent("测试");
//        msg1.setType(Msg.TYPE_RECEIVED);
//        msgList.add(msg1);
//        Msg msg2 = new Msg();
//        msg2.setType(Msg.TYPE_SENT);
//        msg2.setContent("测试右边");
//        msgList.add(msg2);
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
        Log.i("fra", "preferences " + preferences);
        json.put("userId" ,preferences.getString("userId",""));
        json.put("userName" ,preferences.getString("userName", ""));
        json.put("sex" ,preferences.getString("sex", ""));
        json.put("friendId","1");
        return json;
    }
}
