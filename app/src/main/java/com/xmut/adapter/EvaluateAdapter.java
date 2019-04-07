package com.xmut.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.activity.R;
import com.xmut.activity.EvaluateActivity;
import com.xmut.hotel.Evaluate;

import java.util.List;

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {

    private List<Evaluate> evaluateList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView title;
        TextView time;
        TextView price;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = view.findViewById(R.id.evaluate_image);
            title = view.findViewById(R.id.evaluate_title);
            time = view.findViewById(R.id.evaluate_time);
            price = view.findViewById(R.id.evaluate_price);
        }
    }
    public EvaluateAdapter(List<Evaluate> evaluateList){
        this.evaluateList = evaluateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.evaluate_item, parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Evaluate evaluate = evaluateList.get(position);
                Intent intent = new Intent(context, EvaluateActivity.class);
                intent.putExtra("title", evaluate.getTitle());
                intent.putExtra("imageId", evaluate.getImageId());
                context.startActivity(intent);
            }
        });

        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evaluate evaluate = evaluateList.get(position);
        holder.title.setText(evaluate.getTitle());
        holder.time.setText(evaluate.getTime());
        holder.price.setText(String.valueOf( evaluate.getPrice()));
        Glide.with(context).load(evaluate.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return evaluateList.size();
    }
}
