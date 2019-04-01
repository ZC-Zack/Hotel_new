package com.xmut.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.example.activity.R;
import com.xmut.adapter.HomeFragmentAdapter;
import com.xmut.fragment.ChatFragment;
import com.xmut.fragment.FriendFragment;
import com.xmut.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView  bottomNavigationView;
    private HomeFragmentAdapter homeFragmentAdapter;
    private ViewPager viewPager;
    private MenuItem menuItem;
    List<Fragment> fragmentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.menu_chat:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.menu_friend:
                        viewPager.setCurrentItem(2);
                        return true;
                        default:
                            break;

                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.main_frame);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ChatFragment());
        fragmentList.add(new FriendFragment());
        homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(homeFragmentAdapter);
        viewPager.setCurrentItem(0);
    }


}
