package com.xmut.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.activity.R;
import com.xmut.adapter.OrderRecyclerAdaper;
import com.xmut.hotel.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private Order order;
    private List<Order> list;
    private OrderRecyclerAdaper orderRecyclerAdaper;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initOrder();
        initRecyclerView();
    }

    private void initOrder(){
        list = new ArrayList<>();
        order = new Order();
        order.setRoomName("测试");
        order.setImageId(R.drawable.room);
        order.setPrice(100);
        order.setCheckIn(new Date());
        order.setCheckOut(new Date());

        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);
        list.add(order);

    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.order_recycle);
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView .setLayoutManager(gridLayoutManager);
        orderRecyclerAdaper = new OrderRecyclerAdaper(list);
        recyclerView.setAdapter(orderRecyclerAdaper);
    }
}
