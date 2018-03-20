package com.example.derek.ui.teachers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.derek.ui.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class T_AddCourseActivity extends AppCompatActivity {

    private Button btn_upload_v;
    private Button btn_upload_pic;

    private int mYear1, mMonth1, mDay1;

    private TextView mDateDisplay1, mDateDisplay2, mDateDisplay3;
    private Button mPickDate1, mPickDate2, mPickDate3;

    private int date_id;

    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t__add_course);

        btn_upload_v = (Button) findViewById(R.id.btn_upload_video);
        btn_upload_pic = (Button) findViewById(R.id.btn_upload_picture);

        // check if the permission is granted
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                return;
            }
        }

        enable_button();

        // select date
        mDateDisplay1 = (TextView) findViewById(R.id.showMyDate1);
        mDateDisplay2 = (TextView) findViewById(R.id.showMyDate2);
        mDateDisplay3 = (TextView) findViewById(R.id.showMyDate3);

        mPickDate1 = (Button) findViewById(R.id.myDatePickerButton1);
        mPickDate2 = (Button) findViewById(R.id.myDatePickerButton2);
        mPickDate3 = (Button) findViewById(R.id.myDatePickerButton3);

        mPickDate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                date_id = 1;
            }
        });
        mPickDate2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                date_id = 2;
            }
        });
        mPickDate3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                date_id = 3;
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear1 = c.get(Calendar.YEAR);
        mMonth1 = c.get(Calendar.MONTH);
        mDay1 = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        for(int i=1; i<=3; i++) {
            date_id = i;
            updateDisplay();
        }

    }

    private void updateDisplay() {
        switch (date_id) {
            case 1:
                this.mDateDisplay1.setText(
                        new StringBuilder()
                                // Month is 0 based so add 1
                                .append(mMonth1 + 1).append("-")
                                .append(mDay1).append("-")
                                .append(mYear1).append(" "));
                break;
            case 2:
                this.mDateDisplay2.setText(
                        new StringBuilder()
                                // Month is 0 based so add 1
                                .append(mMonth1 + 1).append("-")
                                .append(mDay1).append("-")
                                .append(mYear1).append(" "));
                break;
            case 3:
                this.mDateDisplay3.setText(
                        new StringBuilder()
                                // Month is 0 based so add 1
                                .append(mMonth1 + 1).append("-")
                                .append(mDay1).append("-")
                                .append(mYear1).append(" "));
                break;
        }

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear1 = year;
                    mMonth1 = monthOfYear;
                    mDay1 = dayOfMonth;

                    updateDisplay();

                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear1, mMonth1, mDay1);
        }
        return null;
    }




    private void enable_button() {
        btn_upload_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(T_AddCourseActivity.this)
                        .withRequestCode(10)
                        .start();
            }
        });
        btn_upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(T_AddCourseActivity.this)
                        .withRequestCode(10)
                        .start();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            enable_button();
        }else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK) {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    File f = new  File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type = getMimeType(f.getPath());

                    String file_path = f.getAbsolutePath();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type", content_type)
                            .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/")+1))
                            .build();

                    // put backend server url here !!!
                    Request request = new Request.Builder()
                            .url("")
                            .post(request_body)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();
                        if(!response.isSuccessful()) {
                            throw new IOException("Error: " + response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();
        }
    }

    private String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}
