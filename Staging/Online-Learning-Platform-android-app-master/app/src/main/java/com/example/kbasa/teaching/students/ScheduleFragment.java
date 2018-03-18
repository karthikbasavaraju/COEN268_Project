package com.example.kbasa.teaching.students;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.example.kbasa.teaching.DataTypes.MyDate;
import com.example.kbasa.teaching.DataTypes.Schedule;
import com.example.kbasa.teaching.ListViewAdapter;
import com.example.kbasa.teaching.R;
import com.example.kbasa.teaching.teachers.ViewCourseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ScheduleFragment extends Fragment {

    String user;
    String buttonId = null;
    Map<String, Map<String, String>> vector;
    Vector<String> tagVector;
    Map<String, String> onGoing;
    private ListView lvProduct;
    private ListViewAdapter adapter;
    private Vector<Map<String, String>> past;
    private Vector<Map<String, String>> upcoming;
    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        past = new Vector<>();
        upcoming = new Vector<>();
        onGoing = new HashMap() {{
            put("courseName", "No on-going");
        }};

        DatabaseReference course = FirebaseDatabase.getInstance().getReference("Course");

        course.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector = new HashMap<>();
                tagVector = new Vector<>();
                Log.i("please", String.valueOf(dataSnapshot.getChildrenCount()));
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String temp = dataSnapshot1.getKey();
                    Map<String, String> v = new HashMap<>();
                    v.put("courseId", temp);
                    String professorName = "";
                    String courseName = "";
                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                        if (dataSnapshot2.getKey().equals("courseName")) {
                            v.put(dataSnapshot2.getKey(), dataSnapshot2.getValue(String.class));
                            courseName = dataSnapshot2.getValue(String.class);
                        } else if (dataSnapshot2.getKey().equals("profileUri")) {
                            v.put(dataSnapshot2.getKey(), dataSnapshot2.getValue(String.class));
                        } else if (dataSnapshot2.getKey().equals("name")) {
                            v.put(dataSnapshot2.getKey(), dataSnapshot2.getValue(String.class));
                            professorName = dataSnapshot2.getValue(String.class);
                        } else if (dataSnapshot2.getKey().equals("tags")) {
                            ArrayList tempTag = (ArrayList) dataSnapshot2.getValue();
                            String tempString = "";
                            for (Object tags : tempTag) {
                                tempString += tags.toString() + " ";
                            }
                            tempString = courseName + " " + professorName + " " + tempString;
                            tagVector.add(tempString);
                        }
                    }
                    vector.put(temp, v);
                }
                Bundle b = getActivity().getIntent().getExtras();
                user = b.getString("user");
                if (user.equalsIgnoreCase("student")) {
                    user = "Student";
                } else
                    user = "Teacher";

                DatabaseReference student = FirebaseDatabase.getInstance().getReference(user);
                student.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot scheduleList = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("courseTaken").child("schedules");
                        Log.i("Schedule count", String.valueOf(scheduleList.getChildrenCount()));


                        for (DataSnapshot dataSnapshot1 : scheduleList.getChildren()) {
                            Schedule details = dataSnapshot1.getValue(Schedule.class);
                            MyDate myDate = details.getMyDate();
                            int type = myDate.compare(60);
                            if (type == 0) {
                                onGoing = new HashMap(vector.get(dataSnapshot1.getKey()));
                                buttonId = details.getOtherPersonsId();
                            } else if (type == 1) {
                                upcoming.add(vector.get(dataSnapshot1.getKey()));
                            } else
                                past.add(vector.get(dataSnapshot1.getKey()));

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // get the past courses from database

        lvProduct = (ListView) view.findViewById(R.id.listview);
        adapter = new ListViewAdapter(getContext(), past);
        lvProduct.setAdapter(adapter);

        Button btn_past = view.findViewById(R.id.btn_past);
        btn_past.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database

                adapter.clearList();
                adapter.setList(past);
                lvProduct.setAdapter(adapter);

                lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (user.equalsIgnoreCase("student")) {
                            Intent intent = new Intent(getActivity().getApplicationContext(), EnrollActivity.class);
                            intent.putExtra("courseId", (past.get(i)).get("courseId"));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity().getApplicationContext(), ViewCourseActivity.class);
                            intent.putExtra("courseId", (past.get(i)).get("courseId"));
                            startActivity(intent);
                        }
                    }
                });
                return;
            }
        });

        Button btn_ongoing = view.findViewById(R.id.btn_ongoing);
        btn_ongoing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database
                adapter.clearList();
                adapter.setList(new Vector<Map<String, String>>() {{
                    add(onGoing);
                }});
                lvProduct.setAdapter(adapter);
                lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // Get teacher/student buttonID from 11Sight
                        if (user.equals("Teacher") && buttonId != null) {


                            DatabaseReference student = FirebaseDatabase.getInstance().getReference("Student").child(buttonId).child("elevenUserName");

                            student.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String otherButtonId = dataSnapshot.getValue(String.class);

                                    IISightSDKManager.getInstance().makeCall(otherButtonId,
                                            getContext());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        } else if (buttonId != null) {
                            DatabaseReference student = FirebaseDatabase.getInstance().getReference("Teacher").child(buttonId).child("elevenUserName");

                            student.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String otherButtonId = dataSnapshot.getValue(String.class);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                });
                return;
            }
        });

        Button btn_upcoming = view.findViewById(R.id.btn_upcoming);
        btn_upcoming.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get the past courses from database
                adapter.clearList();
                adapter.setList(upcoming);
                lvProduct.setAdapter(adapter);

                lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if (user.equalsIgnoreCase("student")) {
                            Intent intent = new Intent(getActivity().getApplicationContext(), EnrollActivity.class);
                            intent.putExtra("courseId", (past.get(i)).get("courseId"));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity().getApplicationContext(), ViewCourseActivity.class);
                            intent.putExtra("courseId", (past.get(i)).get("courseId"));
                            startActivity(intent);
                        }
                    }
                });
                return;
            }
        });

        return view;
    }


}
