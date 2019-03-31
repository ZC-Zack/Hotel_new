package com.xmut.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.fragment.ChatFragment;
import com.xmut.fragment.FriendFragment;
import com.xmut.fragment.HomeFragment;

import java.util.List;

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGER_COUNT = 3;

    List<Fragment> fragmentList;
    FragmentManager fragmentManager;

    public HomeFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList){
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.fragmentManager = fragmentManager;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
