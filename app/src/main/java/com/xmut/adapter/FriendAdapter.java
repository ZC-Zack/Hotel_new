package com.xmut.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.activity.EvaluateActivity;
import com.xmut.fragment.ChatFragment;
import com.xmut.hotel.Evaluate;
import com.xmut.hotel.Friend;
import com.xmut.hotel.Room;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;

    private List<Friend> friendList;
    private SharedPreferences.Editor editor;
    private ViewPager viewPager;

    class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView friendImage;
        private TextView friendName;
        private TextView friendNumber;

        public ViewHolder(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            friendName = itemView.findViewById(R.id.friend_name);
            friendNumber = itemView.findViewById(R.id.friend_number);
            friendImage = itemView.findViewById(R.id.friend_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Friend friend = friendList.get(getAdapterPosition());
                    editor.putString("friendId", friend.getUserId());
                    editor.apply();
//                    viewPager.setCurrentItem(0);
                }
            });
        }
    }

    public FriendAdapter(List<Friend> list, SharedPreferences.Editor editor){
        friendList = list;
        this.editor = editor;
//        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);
        final ViewHolder holder=new ViewHolder(view);

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
