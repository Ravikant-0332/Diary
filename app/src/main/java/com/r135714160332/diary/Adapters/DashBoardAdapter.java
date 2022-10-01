package com.r135714160332.diary.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.r135714160332.diary.Activities.DashBoard;
import com.r135714160332.diary.Activities.DiaryPage;
import com.r135714160332.diary.Models.Entry;
import com.r135714160332.diary.R;

import java.util.ArrayList;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.ViewHolder>{

    ArrayList<Entry> entries;
    Context context;

    public DashBoardAdapter(Context context, ArrayList<Entry> entries) {
        this.entries = entries;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_adapter_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.getRv_time_tv().setText(TextViewFormatToCardFormat(entries.get(position).getTimeStamp()));
        String text = entries.get(position).getContent();
        if(text.length()>100){
            text = text.substring(0,97).trim()+"...";
        }
        holder.getRv_content_tv().setText(text);

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DiaryPage.class);
                intent.putExtra("time",entries.get(position).getTimeStamp());
                intent.putExtra("content", entries.get(position).getContent());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rv_content_tv, rv_time_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_time_tv = itemView.findViewById(R.id.rv_time_tv);
            rv_content_tv = itemView.findViewById(R.id.rv_content_tv);
        }

        public TextView getRv_content_tv() {
            return rv_content_tv;
        }

        public TextView getRv_time_tv() {
            return rv_time_tv;
        }

        public View getView(){return itemView;}
    }
    private String TextViewFormatToCardFormat(String S){
        return S.replaceAll(", ","\n");
    }
}
