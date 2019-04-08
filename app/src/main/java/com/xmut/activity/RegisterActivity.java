package com.xmut.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.activity.R;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.ApplyUser;
import com.xmut.hotel.User;

import java.io.File;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText passwordAgain;
    private Button login;
    private CheckBox rememberPass;
    private ImageView ivHead;//头像显示
    private Button btnTakephoto;//拍照
    private Button btnPhotos;//相册
    private Bitmap head;//头像Bitmap
    private static String path = "/sdcard/myHead/";
    private List<User> userList;

    private OkHttpConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        passwordAgain = findViewById(R.id.password_again);
        initUsers();
        Button registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String password_second = passwordAgain.getText().toString();
                if("".equals(account) || "".equals(password) || "".equals(password_second)){
                    Log.i("accout","account"+account);
                    Toast.makeText(RegisterActivity.this, "账号或者密码不能为空", Toast.LENGTH_LONG).show();
                }else{
                    for(User user: userList){
                        if(user.getUserId().equals(account)){
                            Toast.makeText(RegisterActivity.this, "账号已被注册", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                    if(password.equals(password_second)) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("userId", account);
                        jsonObject.put("userName", account);
                        jsonObject.put("password", password);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                connection = new OkHttpConnection();
                                connection.postAddPost(jsonObject, "setUser");
                            }
                        }).start();
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterActivity.this, "两次密码输入不同", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button returnlogin = (Button) findViewById(R.id.return_login);
        returnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    private void initView() {
        //初始化控件
        btnPhotos = (Button) findViewById(R.id.btn_photos);
        btnTakephoto = (Button) findViewById(R.id.btn_takephoto);
        btnPhotos.setOnClickListener(this);
        btnTakephoto.setOnClickListener(this);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            ivHead.setImageDrawable(drawable);
        } else {
            /**
             *	如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_photos:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            case R.id.btn_takephoto:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                        "head.jpg")));
                startActivityForResult(intent2, 2);//采用ForResult打开
                break;
                default:
                    break;
        }
    }
    private void initUsers(){
        if(userList == null){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    connection = new OkHttpConnection();
                    String response = connection.getData("getUser");
                    userList = JSONArray.parseArray(response, User.class);
                }
            }).start();
        }
    }

}

