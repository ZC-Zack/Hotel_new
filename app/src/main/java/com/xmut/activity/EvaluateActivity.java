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

import com.bumptech.glide.Glide;
import com.example.activity.R;

public class EvaluateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        Intent intent=getIntent();
        String title = intent.getStringExtra("title");
        int evaluateImageId = intent.getIntExtra("imageId",0);
        Log.i("tag", "1");
        Toolbar toolbar = findViewById(R.id.evaluate_toolbar);
        Log.i("tag", "2" + toolbar);
        CollapsingToolbarLayout collapsingToolbar= findViewById(R.id.evaluate_collapsing);
        ImageView imageView = findViewById(R.id.evaluate_image);
        TextView evaluateContent = findViewById(R.id.evaluate_text);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(title);
        Log.i("tag", "3");
        Glide.with(this).load(evaluateImageId).into(imageView);
        String content = generateEvaluateContent();
        evaluateContent.setText(content);
    }

    private String generateEvaluateContent(){
        StringBuilder evaluateContent =new StringBuilder();
        for(int i=0;i<500;i++){
            evaluateContent.append("很不错");
        }
        return evaluateContent.toString();
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
