package com.example.user.projectmid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 10-11-2017.
 */


//dataBase class extending SQLiteOpenHelper
public class DataBaseHelper extends SQLiteOpenHelper {

    //constructor setting context,Databasename,DataBaseVersion
    public DataBaseHelper(Context context) {
        super(context, Entry.DATABASE_NAME, null, Entry.DATABASE_VERSION);
    }


   //onCreate method to create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //query to Create Table
        String createTable = "CREATE TABLE " + Entry.TABLE_NAME  + "("
                + Entry.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Entry.KEY_TITLE + " TEXT, "
                +Entry.KEY_DESCRIPTION+" TEXT, "
                + Entry.KEY_DATE +" TEXT, "
                + Entry.KEY_STATUS + " INTEGER )";
        //executing query
        sqLiteDatabase.execSQL(createTable);

    }
        //onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
