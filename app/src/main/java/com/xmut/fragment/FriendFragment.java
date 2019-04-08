package com.xmut.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.activity.R;
import com.xmut.activity.MainActivity;
import com.xmut.adapter.FriendAdapter;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.ApplyUser;
import com.xmut.hotel.Friend;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FriendFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private GridLayoutManager gridLayoutManager;

    private List<Friend> friendList;
    private OkHttpConnection okHttpConnection;
    private SharedPreferences preferences;
    private JSONObject json;
    private SharedPreferences.Editor editor;

    private Activity activity;
    private ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.friend_layout, group, false);
        recyclerView = view.findViewById(R.id.friend_recycle);
        okHttpConnection = new OkHttpConnection();
        init();
        initFriend();
        return  view;
    }
    private void initRecycleView(List<Friend> friendList){
        friendAdapter = new FriendAdapter(friendList, editor,viewPager);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initFriend(){
        friendList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = okHttpConnection.postAddPost(json, "friend");
                friendList = JSONArray.parseArray(response, Friend.class);
                showResponse(friendList);
//                recyclerView.setAdapter(friendAdapter);
//                recyclerView.setLayoutManager(gridLayoutManager);
                onCreate(null);
            }
        }).start();
    }
    private void showResponse(final List<Friend> list){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleView(list);
            }
        });
    }

    public void init(){
        okHttpConnection = new OkHttpConnection();
        preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        editor = getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();
        json = new JSONObject();
        String userId = preferences.getString("userId", "");
        String userName = preferences.getString("userName","");
        String sex = preferences.getString("sex", "");
        json.put("userId", userId);
        json.put("userName", userName);
        json.put("sex", sex);
    }
}
