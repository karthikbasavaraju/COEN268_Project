package com.example.kbasa.teaching.DataTypes;

/**
 * Created by kbasa on 2/26/2018.
 */

public class Schedule {


    String otherPersonsId;
    String otherName;

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Schedule(String otherPersonsId, String otherName, MyDate myDate) {

        this.otherPersonsId = otherPersonsId;
        this.otherName = otherName;
        this.myDate = myDate;
    }

    MyDate myDate;

    public Schedule(String otherPersonsId, MyDate myDate) {
        this.otherPersonsId = otherPersonsId;
        this.myDate = myDate;
    }

    public Schedule() {
    }


    public String getOtherPersonsId() {

        return otherPersonsId;
    }

    public void setOtherPersonsId(String otherPersonsId) {
        this.otherPersonsId = otherPersonsId;
    }

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }
}
