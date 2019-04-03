package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;

public class HomeSecondFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_second_layout, group, false);
        return view;
    }

}
