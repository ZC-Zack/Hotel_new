package com.xmut.adapter;



import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.activity.RoomActivity;
import com.xmut.hotel.Room;

import java.util.List;


public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private Context context;

    private List<Room> roomList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView hotelImage;
        private TextView hotelName;
        private TextView hotelPrice;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            hotelImage = view.findViewById(R.id.hotel_image);
            hotelName = view.findViewById(R.id.hotel_name);
            hotelPrice = view.findViewById(R.id.hotel_price);
        }
    }

    public HotelAdapter(List<Room> roomList){
        this.roomList = roomList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int image = R.drawable.room;
        Room room = roomList.get(position);
        holder.hotelName.setText(room.getName());
        holder.hotelPrice.setText(String.valueOf(room.getPrice()));
        Glide.with(context).load(image).into(holder.hotelImage);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }
}
