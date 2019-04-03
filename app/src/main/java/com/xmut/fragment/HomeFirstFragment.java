package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.adapter.HotelAdapter;
import com.xmut.hotel.Room;

import java.util.ArrayList;
import java.util.List;


public class HomeFirstFragment extends Fragment {

    private View view;
    private List<Room> roomList;
    private HotelAdapter hotelAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_first_layout, group, false);
        initHotel();
        initRecycleView();
        return  view;
    }

    public void initRecycleView(){
        recyclerView = view.findViewById(R.id.hotel_recycle);
        hotelAdapter = new HotelAdapter(roomList);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void initHotel(){

        Room room = new Room("测试", 100, R.drawable.room);
        roomList = new ArrayList<>();
        roomList.clear();
        roomList.add(room);
        roomList.add(room);
        roomList.add(room);
        roomList.add(room);
        roomList.add(room);
    }
}
