package com.example.kbasa.teaching;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kbasa on 3/15/2018.
 */

public class ValidationHelper {

    public static boolean validate(EditText[] fields) {
        for (EditText currentField : fields) {
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateFilePicker(TextView[] fields) {
        for (TextView currentField : fields) {
            if (currentField.getText().toString().equals("No picture selected") ||
                    currentField.getText().toString().equals("No video selected")) {
                return false;
            }
        }
        return true;
    }
}
