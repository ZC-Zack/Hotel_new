package com.xmut.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.hotel.Room;

import java.util.Calendar;

public class RoomActivity extends AppCompatActivity {
    private EditText mEditText;
    public static final String ROOM_NAME="room_name";
    public static final String ROOM_IMAGE_ID="room_image_id";
    public static final String ROOM_PRICE="room_price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mEditText = (EditText) findViewById(R.id.editText1);
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDatePickDlg();
                }
            }
        });


        Intent intent=getIntent();
        String roomName=((Intent) intent).getStringExtra(ROOM_NAME);
        String roomPrice=((Intent) intent).getStringExtra(ROOM_PRICE);
        int roomImageId=intent.getIntExtra(ROOM_IMAGE_ID,0);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView roomImageView=(ImageView)findViewById(R.id.room_image_view);
        TextView roomContentText=(TextView)findViewById(R.id.price);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(roomName);
        Glide.with(this).load(roomImageId).into(roomImageView);
        String roomContent=generateFruitContent(roomPrice);
        roomContentText.setText(roomContent);
    }

    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(RoomActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                RoomActivity.this.mEditText.setText(year + "年" + monthOfYear + "月" + dayOfMonth+"日");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private String generateFruitContent(String roomName){
        StringBuilder roomContent=new StringBuilder();
        for(int i=0;i<1;i++){
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




