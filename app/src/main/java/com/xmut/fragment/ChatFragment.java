package com.xmut.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.activity.R;
import com.xmut.adapter.MsgAdapter;
import com.xmut.hotel.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private View view;
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        view = inflater.inflate(R.layout.chat_layout, group, false);
        initMsg();
        inputText = view.findViewById(R.id.input_text);
        send = view.findViewById(R.id.send_btn);
        msgRecyclerView = view.findViewById(R.id.chat_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg();
                    msg.setContent(content);
                    msg.setType(Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
        return  view;
    }

    private void initMsg(){
        Msg msg1 = new Msg();
        msg1.setContent("测试");
        msg1.setType(Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg();
        msg2.setType(Msg.TYPE_SENT);
        msg2.setContent("测试右边");
        msgList.add(msg2);
    }
}
