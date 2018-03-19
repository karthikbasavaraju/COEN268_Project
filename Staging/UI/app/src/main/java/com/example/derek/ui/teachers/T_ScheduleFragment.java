package com.example.derek.ui.teachers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.derek.ui.Item;
import com.example.derek.ui.ListViewAdapter;
import com.example.derek.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class T_ScheduleFragment extends Fragment {


    public T_ScheduleFragment() {
        // Required empty public constructor
    }

    private List<Item> res_list;
    private ListView lvProduct;
    private ListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_t__schedule, container, false);

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
                list.add(new Item("Past Course 1 with Student E"));
                list.add(new Item("Past Course 2 with Student G"));
                list.add(new Item("Past Course 3 with Student W"));
                list.add(new Item("Past Course 4 with Student Z"));
                list.add(new Item("Past Course 5  with Student Q"));
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
                list.add(new Item("Ongoing Course with Student J"));
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
                list.add(new Item("Upcoming Course 1 with Student A"));
                list.add(new Item("Upcoming Course 2 with Student P"));
                adapter.clearList();
                adapter.setList(list);
                lvProduct.setAdapter(adapter);
                return;
            }
        });

        return view;
    }

}
