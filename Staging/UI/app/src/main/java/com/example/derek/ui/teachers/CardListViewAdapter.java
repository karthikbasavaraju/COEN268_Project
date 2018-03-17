package com.example.derek.ui.teachers;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.derek.ui.R;

import java.util.ArrayList;

/**
 * Created by derek on 2018/3/5.
 */

public class CardListViewAdapter extends RecyclerView.Adapter<CardListViewAdapter.ViewHolder> {

    private ArrayList<String> dataset;
    private final View.OnClickListener mOnClickListener;

    public CardListViewAdapter(ArrayList<String> dataset) {
        this.dataset = dataset;

        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), T_EditCourseActivity.class);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public CardListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_course, parent, false);
        v.setOnClickListener(mOnClickListener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
        }
    }
}
