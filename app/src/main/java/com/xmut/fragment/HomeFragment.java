package com.xmut.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.adapter.HomeTabAdapter;
import com.xmut.adapter.HotelAdapter;
import com.xmut.hotel.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeTabAdapter homeTabAdapter;

    private List<Fragment> list;
    private String[] titles = {"酒店", "待定"};

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_layout, group, false);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        init();
        return  view;
    }

    private void init(){
        tabLayout = view.findViewById(R.id.home_tab);
        viewPager = view.findViewById(R.id.home_pager);
        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        tabLayout.setOnTabSelectedListener(this);

        list = new ArrayList<>();
        list.add(new HomeFirstFragment());
        list.add(new HomeSecondFragment());

        tabLayout.setupWithViewPager(viewPager);
        homeTabAdapter = new HomeTabAdapter(getChildFragmentManager(), titles, list);
        viewPager.setAdapter(homeTabAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /*public void initRecycleView(){
        recyclerView = view.findViewById(R.id.hotel_recycle);
        hotelAdapter = new HotelAdapter(hotelList);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
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

    }*/
}
