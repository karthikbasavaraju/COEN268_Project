package com.example.derek.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by derek on 2018/3/5.
 */

class MyRecylcerViewAdapter extends RecyclerView.Adapter<MyRecylcerViewAdapter.ViewHolder> {

    private ArrayList<String> dataset;
    private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public MyRecylcerViewAdapter(ArrayList<String> dataset) {
        this.dataset = dataset;
    }

    @Override
    public MyRecylcerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        v.setOnClickListener(mOnClickListener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyRecylcerViewAdapter.ViewHolder holder, int position) {
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
