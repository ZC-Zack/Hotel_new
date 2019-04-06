package com.xmut.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.activity.R;

public class RoomActivity extends AppCompatActivity {

    public static final String ROOM_NAME="room_name";
    public static final String ROOM_IMAGE_ID="room_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("RoomActivity","111111111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Intent intent=getIntent();
        String roomName=((Intent) intent).getStringExtra(ROOM_NAME);
        int roomImageId=intent.getIntExtra(ROOM_IMAGE_ID,0);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView roomImageView=(ImageView)findViewById(R.id.room_image_view);
        TextView roomContentText=(TextView)findViewById(R.id.room_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(roomName);
        Glide.with(this).load(roomImageId).into(roomImageView);
        String roomContent=generateFruitContent(roomName);
        roomContentText.setText(roomContent);
    }

    private String generateFruitContent(String roomName){
        StringBuilder roomContent=new StringBuilder();
        for(int i=0;i<500;i++){
            roomContent.append(roomName);
        }
        return roomContent.toString();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
