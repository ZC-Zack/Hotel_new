package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.example.activity.R;
import com.xmut.adapter.HotelAdapter;
import com.xmut.drawUI.HttpConnection;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.Room;

import java.util.List;


public class HomeFirstFragment extends Fragment {

    private View view;
    private List<Room> roomList;
    private HotelAdapter hotelAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private HttpConnection httpConnection;
    private OkHttpConnection okHttpConnection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_first_layout, group, false);
        initHotel();
        return  view;
    }

    public void initRecycleView(List<Room> list){
        recyclerView = view.findViewById(R.id.hotel_recycle);
        hotelAdapter = new HotelAdapter(list);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void initHotel(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*httpConnection = new HttpConnection();
                String response = httpConnection.getTable("room");*/
                okHttpConnection = new OkHttpConnection();
                String response = okHttpConnection.getData("room");
                roomList = JSONArray.parseArray(response, Room.class);
                //Log.i("test", "roomList " + roomList.get(0).toString());
                final Room room = new Room();
                room.setName("测试");
                room.setImageId(R.drawable.room);
                room.setPrice(213);
                roomList.add(room);
                roomList.add(room);
                roomList.add(room);
                roomList.add(room);
                roomList.add(room);
                showResponse(roomList);
                onCreate(null);
            }
        }).start();

    }
    private void showResponse(final List<Room> list){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleView(list);
            }
        });
    }
}
