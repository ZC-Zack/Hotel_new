package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.adapter.FriendAdapter;
import com.xmut.hotel.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Friend> friendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.friend_layout, group, false);
        initFriend();
        return  view;
    }
    private void initRecycleView(List<Friend> friendList){
        recyclerView = view.findViewById(R.id.friend_recycle);
        friendAdapter = new FriendAdapter(friendList);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initFriend(){
        friendList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Friend friend1 = new Friend();
                friend1.setUserId("131312321");
                friend1.setUsername("测试");
                friend1.setImageId(R.drawable.men);
                Friend friend2 = new Friend();
                friend2.setUserId("131312321");
                friend2.setUsername("测试");
                friend2.setImageId(R.drawable.women);
                friendList.add(friend1);
                friendList.add(friend1);
                friendList.add(friend2);
                friendList.add(friend2);
                friendList.add(friend1);
                friendList.add(friend1);
                friendList.add(friend2);
                friendList.add(friend1);
                friendList.add(friend1);
                showResponse(friendList);
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
}
