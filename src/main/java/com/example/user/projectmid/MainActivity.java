package com.example.user.projectmid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//MainActivity extending AppCompatActivity
public class MainActivity extends AppCompatActivity {

//making objects of Toolbar,listView,iamgeView,EditText,Button,DatePicker
    Toolbar toolbar;
    ListView listView;
    ImageView plus, complete;
    EditText cTitle, cDesc;
    Button cSave, cCancel;
    DatePicker datePicker;
    //boolean doubleBackToExitPressedOnce = false;
 //   private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
   // private long mBackPressed;
 private static long back_pressed;

//overwridding OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setting ContentView as acitivty_main.xml
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);         //setting toolbar
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list);        //setting listView

         //setting plus,complete Button by its Id
        plus = findViewById(R.id.plus);
        complete = findViewById(R.id.complete);

        //making Object of EntryData Class;
        final EntryData entryData = new EntryData(this);

        //initialising a ArrayList and setting the data Returned from getEntryList() function
        ArrayList<Entry> initentries = entryData.getEntryList();
        //Custom Adapter
        CustomAdapter customAdapter = new CustomAdapter(this, initentries);
        listView.setAdapter(customAdapter);                                           //setting listView with customAdapter

        //setting OnClickListener for plus button
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //displaying a message on Clicking
                Toast.makeText(getApplicationContext(), "Add Button", Toast.LENGTH_SHORT).show();

                //opening Dialog box to Add Data to list
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dailog);     //setting ContentView for Dialog box


                //setting Title,Description,date,save,cancel with there respective ID's
                cTitle = dialog.findViewById(R.id.titleC);
                cDesc = dialog.findViewById(R.id.descriptionC);
                datePicker = dialog.findViewById(R.id.datePicker);
                cSave = dialog.findViewById(R.id.save_btn);
                cCancel = dialog.findViewById(R.id.cancel_btn);


                //setting OnClickListener for save button
                cSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //checking if title and Description is not empty then save Data
                         if (!cTitle.getText().toString().isEmpty() && !cDesc.getText().toString().isEmpty()) {
                            EntryData entryData1 = new EntryData(MainActivity.this);

                            //making object of Entry java Class
                            Entry entry = new Entry();

                            //setting title,description,Date
                            entry.title = cTitle.getText().toString();
                            entry.Desc = cDesc.getText().toString();
                            entry.Date = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
                            entry.status = 0;        //setting status as unfinished that is 0

                            entry.Id = entryData.insert(entry);

                             //setting added item to list View
                            ArrayList<Entry> arrayList = entryData.getEntryList();
                            CustomAdapter customAdapter1 = new CustomAdapter(MainActivity.this, arrayList);
                            listView.setAdapter(customAdapter1);

                            //displaying Toast Message when item get Saved
                            Toast.makeText(MainActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        } else
                            Toast.makeText(MainActivity.this, "Please fill information correctly", Toast.LENGTH_SHORT).show();

                    }

                });

                //setting Listener for Cancel button
                cCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
           //setting Listener for Completed Task button to show the list of Completed task
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "List of completed task", Toast.LENGTH_SHORT).show();


                //Intent to switch Activity from main Activity to Completed Task to show list of Completed Task
                Intent intent = new Intent(MainActivity.this, CompletedTask.class);
                startActivity(intent);
            }
        });
             //setting listener for item clicked of list View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //on CLicking of item opening of Dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dailog);

                //setting updated Title,Description,date
                cTitle = dialog.findViewById(R.id.titleC);
                cDesc = dialog.findViewById(R.id.descriptionC);
                datePicker = dialog.findViewById(R.id.datePicker);
                cSave = dialog.findViewById(R.id.save_btn);
                cCancel = dialog.findViewById(R.id.cancel_btn);

                //updating list
                EntryData entryData1 = new EntryData(MainActivity.this);
                ArrayList<Entry> arrayList = entryData1.getEntryList();
                final Entry entry = arrayList.get(position);

                cTitle.setText(entry.title);
                cDesc.setText(entry.Desc);
                   //setting listener for save button to update the list
                cSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!cTitle.getText().toString().isEmpty() && !cDesc.getText().toString().isEmpty()) {
                            Entry updatedEntry = new Entry();
                            updatedEntry.Id = entry.Id;
                            updatedEntry.title = cTitle.getText().toString();
                            updatedEntry.Desc = cDesc.getText().toString();
                            updatedEntry.Date = String.valueOf(datePicker.getDayOfMonth()) + "/" +
                                    String.valueOf(datePicker.getMonth()) + "/" +
                                    String.valueOf(datePicker.getYear());
                            updatedEntry.status = entry.status;

                            entryData.update(updatedEntry);
                             //updating the list
                            ArrayList<Entry> updatedArrayList = entryData.getEntryList();
                            //custom Adapter for updated list
                            CustomAdapter adapter = new CustomAdapter(MainActivity.this, updatedArrayList);

                            listView.setAdapter(adapter);


                            //diplaying toast message for updated list
                            Toast.makeText(MainActivity.this, "List Updated", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();

                        } else
                            Toast.makeText(MainActivity.this, "Please fill the information correctly", Toast.LENGTH_SHORT).show();

                    }

                });

                //setting listener for cancel button
                cCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });
          //setting long clicking listener for list items
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {

                //making object of entryData class
                EntryData entryData1 = new EntryData(MainActivity.this);
                ArrayList<Entry> entries = entryData1.getEntryList();

                //making object of Entry to display the list of Completed entry
                Entry completedEntry = entries.get(position);
                completedEntry.status=1;

                //calling update method which is marking the item as finished
                entryData1.update(completedEntry);

                //updating list..
                ArrayList<Entry> updatedEntries = entryData1.getEntryList();
                CustomAdapter adapter = new CustomAdapter(MainActivity.this,updatedEntries);
                listView.setAdapter(adapter);
                Toast.makeText(MainActivity.this,"Item Updated",Toast.LENGTH_SHORT).show();

                return true;
            }
        });




    }

    //overwrridding method for on Backpress if pressed twice it will quit the app
    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
    }
