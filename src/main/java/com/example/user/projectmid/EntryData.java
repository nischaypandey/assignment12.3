package com.example.user.projectmid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 10-11-2017.
 */
//EntryData class
public class EntryData {

    //DataBaseHelper class object
    private DataBaseHelper dataBaseHelper;

    // calling Entry Data class
    public EntryData(Context context) {dataBaseHelper =new DataBaseHelper(context);}
    

    //insert method taking entry as a parameter
    public int insert(Entry entry)
{

    //making object of SqliteDatabase and getting writting permission
    SQLiteDatabase db=dataBaseHelper.getWritableDatabase();

    //setting ContentValue
    ContentValues contentValues=new ContentValues();

    //setting title,description,date and description using contentValue
    contentValues.put(Entry.KEY_TITLE,entry.title);
    contentValues.put(Entry.KEY_DESCRIPTION,entry.Desc);
    contentValues.put(Entry.KEY_DATE,entry.Date);
    contentValues.put(Entry.KEY_STATUS,entry.status);

    //inserting data into sqlite table
    long id=db.insert(Entry.TABLE_NAME,null,contentValues);
    db.close();
    return (int) id;
}

      //update method taking entry object as parameter
    public void update(Entry entry) {

        //Creating reference of writable db.
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        //Creating object of ContentValues.
        ContentValues values = new ContentValues();

        //Putting values in the contentValues object.
        values.put(Entry.KEY_TITLE,entry.title);
        values.put(Entry.KEY_DESCRIPTION,entry.Desc);
        values.put(Entry.KEY_DATE,entry.Date);
        values.put(Entry.KEY_STATUS,entry.status);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Entry.TABLE_NAME, values, Entry.KEY_ID + "= ?", new String[] { String.valueOf(entry.Id) });
        db.close(); // Closing database connection
    }


           //getEntryList method of Arraylist type
    public ArrayList<Entry> getEntryList() {
        //Creating reference of writable db.
        SQLiteDatabase db=dataBaseHelper.getReadableDatabase();

        //query to select id,title,date,description,and status from table
        String selectQuery =  "SELECT  " +
                Entry.KEY_ID + "," +
                Entry.KEY_TITLE + "," +
                Entry.KEY_DESCRIPTION+","+
                Entry.KEY_DATE+","+
                Entry.KEY_STATUS+
                " FROM " + Entry.TABLE_NAME;


        ArrayList<Entry> arrayList=new ArrayList<>();

        //making cursor object to access rows of table
        Cursor cursor=db.rawQuery(selectQuery,null);

        //checking if cursor is on first location
        if(cursor.moveToFirst())
        {
            do{
                //making entry object
                Entry entry=new Entry();

                //getting data from table in id,title,desc,date,status
                entry.Id=cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                entry.title=cursor.getString(cursor.getColumnIndex(Entry.KEY_TITLE));
                entry.Desc=cursor.getString(cursor.getColumnIndex(Entry.KEY_DESCRIPTION));
                entry.Date=cursor.getString(cursor.getColumnIndex(Entry.KEY_DATE));
                entry.status=cursor.getInt(cursor.getColumnIndex((Entry.KEY_STATUS)));


                //adding in arraylist
                arrayList.add(entry);



            }while (cursor.moveToNext());
        }
        Collections.sort(arrayList);
        cursor.close();
        db.close();
        return arrayList;
    }

    //delete method
    public void delete(int entry_Id) {

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Entry.TABLE_NAME, Entry.KEY_ID + "= ?", new String[] { String.valueOf(entry_Id) });
        db.close(); // Closing database connection
    }

         //getArraylist method
    public ArrayList<Entry> getArrayList()
    {
        //Creating reference of writable db.
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        //query to fetch items from list
        String selectQuery =  "SELECT  " +
                Entry.KEY_ID + "," +
                Entry.KEY_TITLE + "," +
                Entry.KEY_DESCRIPTION+","+
                Entry.KEY_DATE+","+
                Entry.KEY_STATUS+
                " FROM " + Entry.TABLE_NAME;

        ArrayList<Entry> arrayList = new ArrayList<Entry>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS))==1) {

                    //making object of entry class
                    Entry entry = new Entry();
                    //getting data from table in id,title,desc,date,status
                    entry.Id = cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                    entry.title = cursor.getString(cursor.getColumnIndex(Entry.KEY_TITLE));
                    entry.Desc = cursor.getString(cursor.getColumnIndex(Entry.KEY_DESCRIPTION));
                    entry.Date = cursor.getString(cursor.getColumnIndex(Entry.KEY_DATE));
                    entry.status = cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS));
                    arrayList.add(entry);
                }

            } while (cursor.moveToNext());
        }
         Collections.sort(arrayList);
        db.close();
        cursor.close();
        return arrayList;
    }
}
