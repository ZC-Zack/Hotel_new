package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;
import com.xmut.adapter.EvaluateAdapter;
import com.xmut.hotel.Evaluate;

import java.util.ArrayList;
import java.util.List;

public class HomeSecondFragment extends Fragment {

    private View view;
    private List<Evaluate> evaluateList;
    private EvaluateAdapter evaluateAdapter;
    private GridLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.home_second_layout, group, false);
        initEvaluate();
        recyclerView = view.findViewById(R.id.evaluate_recycle);
        layoutManager = new GridLayoutManager(this.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        evaluateAdapter = new EvaluateAdapter(evaluateList);
        recyclerView.setAdapter(evaluateAdapter);
        return view;
    }

    public void initEvaluate(){
        evaluateList = new ArrayList<>();
        Evaluate evaluate = new Evaluate();
        evaluate.setImageId(R.drawable.room);
        evaluate.setTitle("主题房间");
        evaluate.setTime("2019-2-22");
        evaluate.setPrice(150);
        for(int i = 0;i < 10; i++){
            evaluateList.add(evaluate);
        }
    }

}
