package com.example.kbasa.teaching;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;


public class ProfileFragment extends Fragment {

    static String picFilePath = null;
    boolean flag1;
    boolean flag2;

    ImageView imageView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        final TextView name = view.findViewById(R.id.tv_name);
        TextView email = view.findViewById(R.id.tv_email);
        imageView = view.findViewById(R.id.imageView);

        email.setText(auth.getEmail());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker().withActivity(getActivity())
                        .withFilter(Pattern.compile("[a-z]+.(jpg|png|gif|bmp)$"))
                        .withRequestCode(1)
                        .start();

            }
        });

        DatabaseReference courseDB = FirebaseDatabase.getInstance().getReference("Teacher").child(auth.getUid());
        courseDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("personalDetails").child("fullName").getValue(String.class) != null) {
                    name.setText(dataSnapshot.child("personalDetails").child("fullName").getValue(String.class));
                    Picasso.with(getContext()).load(dataSnapshot.child("profileUri").getValue(String.class)).into(imageView);
                    flag1 = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference studentDB = FirebaseDatabase.getInstance().getReference("Student").child(auth.getUid());
        studentDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("profilePic").exists()) {
                    String profilePic = dataSnapshot.child("profilePic").getValue(String.class);
                    Picasso.with(getActivity().getApplicationContext()).load(profilePic).into(imageView);
                }
                if (dataSnapshot.child("personalDetails").child("fullName").getValue(String.class) != null) {
                    imageView.setBackground(null);
                    name.setText(dataSnapshot.child("personalDetails").child("fullName").getValue(String.class));
                    Picasso.with(getContext()).load(dataSnapshot.child("profileUri").getValue(String.class)).into(imageView);
                    flag2 = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();


                // 11Sight Logout

                IISightSDKManager.getInstance().logoutUser(new IISightSDKManager.ICallback() {
                    @Override
                    public void process(Object o) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }, new IISightSDKManager.ICallback() {
                    @Override
                    public void process(Object o) {
                    }
                });



            }
        });

        return view;
    }


/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        Log.i("profilePic", picFilePath);

        if (requestCode == 1 && resultCode == -1) {
            File f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            picFilePath = f.getAbsolutePath();

            if (picFilePath != null) {
                final DatabaseReference courseDB = FirebaseDatabase.getInstance().getReference("Teacher").child(FirebaseAuth.getInstance().getUid());
                InputStream picStream = null;
                try {
                    picStream = new FileInputStream(new File(picFilePath));
                } catch (Exception e) {
                    Log.i("louda upload", "path : " + picStream);
                }

                StorageReference mountainsRef = FirebaseStorage.getInstance().getReference();

                UploadTask picUploadTask = mountainsRef.child("pic").putStream(picStream);
                picUploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final Uri profileUri = taskSnapshot.getDownloadUrl();
                        courseDB.updateChildren(new HashMap<String, Object>() {{
                            put("profilePic", profileUri);
                        }});
                    }
                });
            }
        }
    }
*/
}
