package com.xmut.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.activity.R;
import com.xmut.adapter.HotelAdapter;
import com.xmut.hotel.Hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private List<Hotel> hotelList;
    private HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initHotel();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        hotelAdapter = new HotelAdapter(hotelList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(hotelAdapter);
        /*drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);*/
        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logoff, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoff_btn:
                Toast.makeText(this, "logoff", Toast.LENGTH_SHORT);
                break;
        }
        return true;
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
