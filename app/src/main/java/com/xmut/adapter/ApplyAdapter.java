package com.xmut.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.drawUI.GetSharedPreferences;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.ApplyUser;
import com.xmut.hotel.Msg;

import java.util.List;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ViewHolder> {

    private Context context;
    private List<ApplyUser> list;
    private SharedPreferences sharedPreferences;
    private static int clickFlag = 0;

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView applyImage;
        TextView friendName;
        TextView friendSex;
        TextView friendId;
        Button agree_btn;
        Button refuse_btn;

        OkHttpConnection okHttpConnection = new OkHttpConnection();
        public ViewHolder(final View view) {
            super(view);
            cardView = (CardView) view;
            friendName = view.findViewById(R.id.friend_name);
            friendSex = view.findViewById(R.id.friend_sex);
            //friendId = view.findViewById(R.id.friend_apply_id);
           // Log.i("tag", "userId: " + friendId);
            agree_btn = view.findViewById(R.id.agree_btn);
            refuse_btn = view.findViewById(R.id.refuse_btn);
            applyImage = view.findViewById(R.id.apply_img);
            agree_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ApplyUser user = list.get(getAdapterPosition());
                            list.remove(getAdapterPosition());
                            JSONObject json = (JSONObject) JSONObject.toJSON(user);
                            json.put("result", 1);
                            json.put("friendId", sharedPreferences.getString("userId", ""));
                            okHttpConnection.postApplyResult(json, "applyResult");
                            clickFlag = 1;
                        }
                    }).start();
                }

            });
            refuse_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ApplyUser user = list.get(getAdapterPosition());
                            list.remove(getAdapterPosition());
                            JSONObject json = (JSONObject) JSONObject.toJSON(user);
                            json.put("result", -1);
                            json.put("friendId", sharedPreferences.getString("userId", ""));
                            okHttpConnection.postApplyResult(json, "applyResult");
                            clickFlag = 1;
                        }
                    }).start();
                }
            });
        }
    }

    public ApplyAdapter(List<ApplyUser> list, SharedPreferences sharedPreferences){
        this.list = list;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.apply_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int image;
        ApplyUser user = list.get(position);
        holder.friendSex.setText(user.getSex());
        holder.friendName.setText(user.getUserName());
       // holder.friendId.setText(user.getUserId());
        if("男".equals(user.getSex())){
            image = R.drawable.men;
        }else{
            image = R.drawable.women;
        }
        Glide.with(context).load(image).into(holder.applyImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
