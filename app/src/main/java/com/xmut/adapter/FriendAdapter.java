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
import com.xmut.hotel.Friend;
import com.xmut.hotel.Room;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;

    private List<Friend> friendList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView friendImage;
        private TextView friendName;
        private TextView friendNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            friendName = itemView.findViewById(R.id.friend_name);
            friendNumber = itemView.findViewById(R.id.friend_number);
            friendImage = itemView.findViewById(R.id.friend_image);

        }
    }

    public FriendAdapter(List<Friend> list){
        friendList = list;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        int image;
        Friend friend = friendList.get(position);
        holder.friendNumber.setText(friend.getUserName());
        holder.friendName.setText(friend.getUserId());
        if("男".equals(friend.getSex())){
            image = R.drawable.men;
        }else{
            image = R.drawable.women;
        }
        Glide.with(context).load(image).into(holder.friendImage);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}
