package com.xmut.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.hotel.Order;

import java.util.List;

public class OrderRecyclerAdaper extends RecyclerView.Adapter<OrderRecyclerAdaper.ViewHolder> {

    private Context context;
    private List<Order> list;

    public OrderRecyclerAdaper(List<Order> list){
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView orderImage;
        TextView roomName, orderTime, orderPrice;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            orderImage = view.findViewById(R.id.order_image);
            roomName = view.findViewById(R.id.room_text);
            orderTime = view.findViewById(R.id.order_time);
            orderPrice = view.findViewById(R.id.order_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            this.context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = list.get(position);
        holder.roomName.setText(order.getRoomName());
        holder.orderTime.setText(order.getCheckIn().toString() + "-" +order.getCheckOut().toString());
        holder.orderPrice.setText(Double.toString(order.getPrice()));
        Glide.with(context).load(order.getImageId()).into(holder.orderImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
