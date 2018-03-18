package com.example.kbasa.teaching;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.Vector;

/**
 * Created by kbasa on 3/6/2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    Vector<Map<String, String>> course;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, Vector<Map<String, String>> course) {
        this.context = applicationContext;
        this.course = course;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return course.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_gridview, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);

        //    new DownloadImageTask().execute(imageView);
//        Uri profileUri = Uri.parse((course.get(i)).get("profileUri"));
        //      imageView.setTag(profileUri);

        Picasso.with(context).load((course.get(i)).get("profileUri")).into(imageView);

        TextView courseName = view.findViewById(R.id.courseNameTextView);
        courseName.setText((course.get(i)).get("courseName"));
        return view;
    }


}
