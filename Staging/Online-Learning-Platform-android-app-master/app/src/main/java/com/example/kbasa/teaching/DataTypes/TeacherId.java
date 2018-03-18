package com.example.kbasa.teaching.DataTypes;

import java.util.HashMap;

/**
 * Created by kbasa on 2/26/2018.
 */

public class TeacherId {

    HashMap<String, Teacher> id;

    public TeacherId() {

    }

    public TeacherId(HashMap<String, Teacher> id) {
        this.id = id;
    }

    public HashMap<String, Teacher> getId() {
        return id;
    }

    public void setId(HashMap<String, Teacher> id) {
        this.id = id;
    }
}
