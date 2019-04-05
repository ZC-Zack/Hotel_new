package com.xmut.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.adapter.HomeTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeTabAdapter homeTabAdapter;

    private List<Fragment> list;
    private String[] titles = {"酒店", "待定"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_layout, group, false);
        init();
        return  view;
    }

    private void init(){
        tabLayout = view.findViewById(R.id.home_tab);
        viewPager = view.findViewById(R.id.home_pager);
        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(this);

        list = new ArrayList<>();
        list.add(new HomeFirstFragment());
        list.add(new HomeSecondFragment());

        tabLayout.setupWithViewPager(viewPager);
        homeTabAdapter = new HomeTabAdapter(getChildFragmentManager(), titles, list);
        viewPager.setAdapter(homeTabAdapter);

       /*for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }*/
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
