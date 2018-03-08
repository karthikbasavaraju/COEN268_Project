package com.example.derek.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.derek.ui.Item;
import com.example.derek.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek on 2018/3/6.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Item> list;

    public ListViewAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Item item) {
        list.add(item);
        return;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item, null);

        TextView tvName = (TextView) v.findViewById(R.id.title);

        tvName.setText(list.get(position).getName());

        return v;
    }

    public void clearList() {
        list = new ArrayList<>();
    }
}
