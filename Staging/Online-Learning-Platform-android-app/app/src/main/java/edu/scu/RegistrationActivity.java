package edu.scu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.kbasa.teaching.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import edu.scu.DataTypes.PersonalDetails;
import edu.scu.DataTypes.Student;
import edu.scu.DataTypes.Teacher;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repasswordEditText;
    private ToggleButton studentToggleButton;
    private ToggleButton teacherToggleButton;
    private EditText interestsEditText;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }


    public void register(View v) {

        auth = FirebaseAuth.getInstance();

        firstNameEditText = findViewById(R.id.input_first_name);
        lastNameEditText = findViewById(R.id.input_last_name);
        emailEditText = findViewById(R.id.input_email);
        passwordEditText = findViewById(R.id.input_password);
        repasswordEditText = findViewById(R.id.input_password);
        studentToggleButton = findViewById(R.id.btn_students);
        teacherToggleButton = findViewById(R.id.btn_teachers);
        interestsEditText = findViewById(R.id.input_interests);


        if (studentToggleButton.isChecked())
            studentRegister();
        else if (teacherToggleButton.isChecked())
            teacherRegister();
        else
            Toast.makeText(RegistrationActivity.this, "select which type of user", Toast.LENGTH_SHORT).show();

    }

    public void checkStudent(View v) {

        studentToggleButton = findViewById(R.id.btn_students);
        teacherToggleButton = findViewById(R.id.btn_teachers);

        if (studentToggleButton.isChecked()) {
            teacherToggleButton.setChecked(false);
        }
    }


    public void checkTeacher(View v) {

        studentToggleButton = findViewById(R.id.btn_students);
        teacherToggleButton = findViewById(R.id.btn_teachers);

        if (teacherToggleButton.isChecked()) {
            studentToggleButton.setChecked(false);
        }
    }

    public void studentRegister() {
        if (passwordEditText.getText().toString().equals(repasswordEditText.getText().toString())) {
            auth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            databaseReference = FirebaseDatabase.getInstance().getReference();
                            databaseReference = databaseReference.child("Student");

                            PersonalDetails personalDetails = new PersonalDetails(firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString(),
                                    emailEditText.getText().toString(), null);

                            StringTokenizer stringTokenizer = new StringTokenizer(interestsEditText.getText().toString(), ",");
                            ArrayList<String> interest = new ArrayList<>();
                            while (stringTokenizer.hasMoreElements()) {
                                interest.add(stringTokenizer.nextElement().toString());
                            }


                            Student student = new Student(personalDetails, interest, null);
                            HashMap hm = new HashMap();
                            hm.put(auth.getUid(), student);
                            databaseReference.updateChildren(hm);
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();


                            String url = "https://sdktest.11sight.com/api/v2/users.json";
                            String u = emailEditText.getText().toString();
                            String pwd = passwordEditText.getText().toString();

                            String fName = firstNameEditText.getText().toString();
                            String lName = lastNameEditText.getText().toString();

                            String jsonString = "{" +
                                    "\"tos\": \"accepted\"," +
                                    "\"user\" :{" +
                                    "\"email\":\"" + u + "\"," +
                                    "\"password\":\"" + pwd + "\"," +

                                    "\"profile_attributes\": {" +
                                    "\"first_name\": \"" + fName + "\"," +
                                    "\"last_name\": \"" + lName + "\"," +
                                    "\"business_name\": \"SCU\"" +
                                    "}" +

                                    "}" +
                                    "}";
                            JSONObject json = null;
                            try {
                                json = new JSONObject(jsonString);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(),
                                            "SUCESS_11Sight_REG", Toast.LENGTH_SHORT).show();
                                    Log.d("11SIGHTLOLBOI", response.toString());
                                        String json = response.toString();
                                        String strip = json.substring(json.indexOf("button_id"),json.length()-1);
                                        String strip2 = strip.substring(strip.indexOf(":\"") + 2, strip.length() -1);
                                        String buttonId = strip2.substring(0, strip2.indexOf("\""));

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(),
                                            "ERROR_11Sight_REG", Toast.LENGTH_SHORT).show();
                                    Log.d("11SIGHTLOLBOI", new String(error.networkResponse.data));
                                }
                            }) {
                                /**
                                 * Passing some request headers
                                 */
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    HashMap<String, String> headers = new HashMap<String, String>();
                                    //headers.put("Content-Type", "application/json");
                                    headers.put("S-Auth-Token", "rse75cohhbajx3x4tc3brhca");
                                    headers.put("Content-Type", "application/json");
                                    return headers;
                                }
                            };
                            RestApi.getInstance(getApplicationContext()).add(req);

                            finish();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(RegistrationActivity.this, "Password doesnt match", Toast.LENGTH_SHORT).show();
        }
    }

    public void teacherRegister() {

        if (passwordEditText.getText().toString().equals(repasswordEditText.getText().toString())) {
            auth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            databaseReference = FirebaseDatabase.getInstance().getReference();
                            databaseReference = databaseReference.child("Teacher");

                            PersonalDetails personalDetails = new PersonalDetails(firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString(),
                                    emailEditText.getText().toString(), null);

                            Teacher teacher = new Teacher(personalDetails, "I am professor", null, null);
                            HashMap hm = new HashMap();
                            hm.put(auth.getUid(), teacher);
                            databaseReference.updateChildren(hm);
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);



                            String url = "https://sdktest.11sight.com/api/v2/users.json";
                            String u = emailEditText.getText().toString();
                            String pwd = passwordEditText.getText().toString();

                            String fName = firstNameEditText.getText().toString();
                            String lName = lastNameEditText.getText().toString();

                            String jsonString = "{" +
                                    "\"tos\": \"accepted\"," +
                                    "\"user\" :{" +
                                    "\"email\":\"" + u + "\"," +
                                    "\"password\":\"" + pwd + "\"," +

                                    "\"profile_attributes\": {" +
                                    "\"first_name\": \"" + fName + "\"," +
                                    "\"last_name\": \"" + lName + "\"," +
                                    "\"business_name\": \"SCU\"" +
                                    "}" +

                                    "}" +
                                    "}";
                            JSONObject json = null;
                            try {
                                json = new JSONObject(jsonString);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(),
                                            "SUCESS_11Sight_REG", Toast.LENGTH_SHORT).show();
                                    Log.d("11SIGHTLOLBOI", response.toString());
                                    String json = response.toString();
                                    String strip = json.substring(json.indexOf("button_id"),json.length()-1);
                                    String strip2 = strip.substring(strip.indexOf(":\"") + 2, strip.length() -1);
                                    String buttonId = strip2.substring(0, strip2.indexOf("\""));

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(),
                                            "ERROR_11Sight_REG", Toast.LENGTH_SHORT).show();
                                    Log.d("11SIGHTLOLBOI", new String(error.networkResponse.data));
                                }
                            }) {
                                /**
                                 * Passing some request headers
                                 */
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    HashMap<String, String> headers = new HashMap<String, String>();
                                    //headers.put("Content-Type", "application/json");
                                    headers.put("S-Auth-Token", "rse75cohhbajx3x4tc3brhca");
                                    headers.put("Content-Type", "application/json");
                                    return headers;
                                }
                            };
                            RestApi.getInstance(getApplicationContext()).add(req);



                            Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(RegistrationActivity.this, "Password doesnt match", Toast.LENGTH_SHORT).show();
        }
    }
}


