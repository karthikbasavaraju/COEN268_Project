package com.example.derek.ui.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.derek.ui.Item;
import com.example.derek.ui.ListViewAdapter;
import com.example.derek.ui.R;

import java.util.ArrayList;
import java.util.List;

public class DateSelectionActivity extends AppCompatActivity {

    private List<Item> res_list;
    private ListView lvProduct;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        res_list = new ArrayList<>();
        res_list.add(new Item("13:00 03/03/2018"));
        res_list.add(new Item("17:00 03/06/2018"));
        res_list.add(new Item("22:00 03/08/2018"));

        lvProduct = (ListView) findViewById(R.id.list_time);
        adapter = new ListViewAdapter(this, res_list);
        lvProduct.setAdapter(adapter);
    }
}
