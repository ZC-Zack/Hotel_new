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
import com.xmut.hotel.Hotel;

import java.util.ArrayList;
import java.util.List;


public class HomeFirstFragment extends Fragment {

    private View view;
    private List<Hotel> hotelList;
    private HotelAdapter hotelAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_layout, group, false);
        initHotel();
        initRecycleView();
        return  view;
    }

    public void initRecycleView(){
        recyclerView = view.findViewById(R.id.hotel_recycle);
        hotelAdapter = new HotelAdapter(hotelList);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        //recyclerView.setAdapter(hotelAdapter);
        //recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void initHotel(){

        Hotel hotel = new Hotel("测试", 100, R.drawable.room);
        hotelList = new ArrayList<>();
        hotelList.clear();
        hotelList.add(hotel);
        hotelList.add(hotel);
        hotelList.add(hotel);
        hotelList.add(hotel);
        hotelList.add(hotel);
    }
}
