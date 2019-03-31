package com.xmut.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.activity.R;
import com.xmut.adapter.HomeFragmentPagerAdapter;
import com.xmut.fragment.ChatFragment;
import com.xmut.fragment.FriendFragment;
import com.xmut.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements RadioGroup.OnCheckedChangeListener  {

    private DrawerLayout drawerLayout;
    private RadioGroup bottemRadio;
    private RadioButton homeButton;
    private RadioButton chatButton;
    private RadioButton friendButton;
    private List<Fragment> bottomFrame;

    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private FriendFragment friendFragment;
    private FragmentManager bottomFragmentManager;

    private FrameLayout frameLayout;

    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        setDefaultFragment();


    }

    /*
    * 导航所有的按钮
    * */
    public void init(){


        bottomFrame = new ArrayList<Fragment>();

        frameLayout = (FrameLayout) findViewById(R.id.main_frame);

        bottemRadio = (RadioGroup) findViewById(R.id.bottom_group);
        homeButton = (RadioButton) findViewById(R.id.home_btn);
        chatButton = (RadioButton) findViewById(R.id.chat_btn);
        friendButton = (RadioButton) findViewById(R.id.friend_btn);

        homeFragment = new HomeFragment();
        chatFragment = new ChatFragment();
        friendFragment = new FriendFragment();

        bottomFrame.add(homeFragment);
        bottomFrame.add(chatFragment);
        bottomFrame.add(friendFragment);

        bottomFragmentManager = getSupportFragmentManager();

        homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(bottomFragmentManager, bottomFrame);

        bottemRadio.setOnCheckedChangeListener(this);
        homeButton.setChecked(true);

    }


    /*
    * 侧边栏
    * */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logoff, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoff_btn:
                Toast.makeText(this, "logoff", Toast.LENGTH_SHORT);
                break;
        }
        return true;
    }

    public void setDefaultFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, chatFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (checkedId){
            case R.id.home_btn:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.main_frame, homeFragment);
                }else{
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.chat_btn:
                if(chatFragment == null){
                    chatFragment = new ChatFragment();
                    fragmentTransaction.add(R.id.main_frame, chatFragment);
                }else{
                    fragmentTransaction.show(chatFragment);
                }
                break;
            case R.id.friend_btn:
                if(friendFragment == null){
                    friendFragment = new FriendFragment();
                    fragmentTransaction.add(R.id.main_frame, friendFragment);
                }else{
                    fragmentTransaction.show(friendFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(homeFragment != null){
            fragmentTransaction.hide(homeFragment);
        }
        if(chatFragment != null){
            fragmentTransaction.hide(chatFragment);
        }
        if(friendFragment != null){
            fragmentTransaction.hide(friendFragment);
        }
    }
}
