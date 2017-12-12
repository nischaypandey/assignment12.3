package com.example.user.projectmid;

import android.support.annotation.NonNull;

/**
 * Created by user on 10-11-2017.
 */
//entry class to set Constants which is implementing Comparable class to Compare Date
public class Entry implements Comparable<Entry> {
      //setting constants for table name,database name,database version,keyId,key title,key Description,key Date and Status
    public static final String TABLE_NAME="todo";
    public static String DATABASE_NAME="data.db";
    public static Integer DATABASE_VERSION=1;


    public static final String KEY_ID="id";
    public static String KEY_TITLE="title";
    public static String KEY_DESCRIPTION="description";
    public static String KEY_DATE="date";
    public static String KEY_STATUS="key_status";


    //declaring constants
    public int Id;
    public String title;
    public String Desc;
    public String Date;
    public int status;



      //compareTo method to compare date
    @Override
    public int compareTo(@NonNull Entry entry) {

        //spliting date into day,month,year
        String[] current=this.Date.split("/");
        String[] passed=entry.Date.split("/");

        int currDay=Integer.parseInt(current[0]);
        int currMonth=Integer.parseInt(current[1]);
        int currYear=Integer.parseInt(current[2]);

        int passedDay=Integer.parseInt(passed[0]);
        int passedMonth=Integer.parseInt(passed[1]);
        int passedYear=Integer.parseInt(passed[2]);

       //comparing dates year wise
        if(currYear<passedYear)
            return -1;
        else if(currYear>passedYear)
            return 1;
            //comparing dates year wise and month wise
        else if(currYear==passedYear && currMonth<passedMonth)
            return -1;
        else if(currYear==passedYear && currMonth>passedMonth)
            return 1;
            //comparing dates year wise,month wise and day wise
        else if(currYear==passedYear && currMonth==passedMonth && currDay<passedDay)
            return -1;
        else if(currYear==passedYear && currMonth==passedMonth && currDay>passedDay)
            return 1;

        return 0;
    }
}

