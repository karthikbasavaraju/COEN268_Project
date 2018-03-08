package com.example.derek.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    public ScheduleFragment() {
        // Required empty public constructor
    }

    private List<Item> res_list;
    private ListView lvProduct;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // get the past courses from database
        res_list = new ArrayList<>();
        res_list.add(new Item("Ongoing Course 1"));

        lvProduct = (ListView) view.findViewById(R.id.listview);
        adapter = new ListViewAdapter(getContext(), res_list);
        lvProduct.setAdapter(adapter);

        Button btn_past = view.findViewById(R.id.btn_past);
        btn_past.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database
                List<Item> list = new ArrayList<>();
                list.add(new Item("Past Course 1"));
                list.add(new Item("Past Course 2"));
                list.add(new Item("Past Course 3"));
                list.add(new Item("Past Course 4"));
                list.add(new Item("Past Course 5"));
                adapter.clearList();
                adapter.setList(list);
                lvProduct.setAdapter(adapter);
                return;
            }
        });

        Button btn_ongoing = view.findViewById(R.id.btn_ongoing);
        btn_ongoing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database
                List<Item> list = new ArrayList<>();
                list.add(new Item("Ongoing Course 1"));
                adapter.clearList();
                adapter.setList(list);
                lvProduct.setAdapter(adapter);
                return;
            }
        });

        Button btn_upcoming = view.findViewById(R.id.btn_upcoming);
        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database
                List<Item> list = new ArrayList<>();
                list.add(new Item("Upcoming Course 1"));
                list.add(new Item("Upcoming Course 2"));
                adapter.clearList();
                adapter.setList(list);
                lvProduct.setAdapter(adapter);
                return;
            }
        });

        return view;
    }

}
