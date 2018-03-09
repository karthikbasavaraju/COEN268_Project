package com.example.derek.ui.teachers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.derek.ui.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class T_HomeFragment extends Fragment {

    private ArrayList<String> res_list;
    private ListView lvProduct;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private CardListViewAdapter adapter;

    public T_HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_t__home, container, false);

        res_list = new ArrayList<>();
        res_list.add("English");
        res_list.add("Spanish");
        res_list.add("Japanese");

        recyclerView = (RecyclerView) view.findViewById(R.id.list_t_courses);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CardListViewAdapter(res_list);
        recyclerView.setAdapter(adapter);

        Button btn_add_course = (Button) view.findViewById(R.id.btn_add_course);
        btn_add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), T_AddCourseActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
