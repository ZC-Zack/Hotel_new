package com.xmut.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.activity.R;
import com.xmut.drawUI.OkHttpConnection;
import com.xmut.hotel.ApplyUser;
import com.xmut.hotel.User;


import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText loginId;
    private EditText userPassword;
    private Button login;
    private CheckBox rememberPass;
    private JSONArray userjson;
    private OkHttpConnection connection;
    private List<User> userList;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initUsers();
        loginId = (EditText)findViewById(R.id.login_text);
        userPassword = (EditText)findViewById(R.id.password_text);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.act_login_phone_loginBut);

        initUsers();

        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            loginId.setText(account);
            userPassword.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                int len = 0;
                String userId = loginId.getText().toString();
                String password = userPassword.getText().toString();
                if ("".equals(userId) || "".equals(password)) {
                    Toast.makeText(LoginActivity.this, "账号或者密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    for (User user : userList) {
                        len++;
                        if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                            editor.putString("userId", user.getUserId());
                            editor.putString("userName", user.getUserName());
                            editor.putString("sex", user.getSex());
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    if(len == userList.size()) {
                        Toast.makeText(LoginActivity.this, "账号或者密码错误", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });

        Button registerButton=(Button)findViewById(R.id.act_login_reg);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        Button returnbtn=(Button)findViewById(R.id.return_btn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }


    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(LoginActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
