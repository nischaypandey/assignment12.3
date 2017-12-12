package com.example.user.projectmid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
//new activity to display the list of completed list
public class CompletedTask extends AppCompatActivity {

    //creating objects of IamgeView,ListView,toolbar
ImageView add,done;
ListView listView;
Toolbar toolbar;


     //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting ContentView as activity_completed_task
        setContentView(R.layout.activity_completed_task);
        toolbar=findViewById(R.id.toolbar2);         //setting toolbar by its id
        setSupportActionBar(toolbar);                 //setting toolbar
        add=findViewById(R.id.add2);                 //setting add button by its id
        done=findViewById(R.id.completed2);          //setting done button by its id
        listView=findViewById(R.id.list2);           //setting list View by its Id

          //setting listener for add button
          add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(getApplicationContext(),"Press back and go to previous page",Toast.LENGTH_LONG).show();
              }
          });

          //setting  listener for done button
          done.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Toast.makeText(getApplicationContext(),"You are already on Completed list page",Toast.LENGTH_LONG).show();
              }
          });
          //making object of entry data
          EntryData entryData=new EntryData(this);
          final ArrayList<Entry> arrayList=entryData.getArrayList();

          //checking if list is empty
          if(arrayList.isEmpty())
          {
              Toast.makeText(getApplicationContext(),"The list is Empty",Toast.LENGTH_LONG).show();
              finish();
          }

          //making custom Adapter object to display the list items
         CustomAdapter customAdapter=new CustomAdapter(CompletedTask.this,arrayList);
         listView.setAdapter(customAdapter);

           //setting on Long press of list items
         listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
             @Override
             public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                 //on long click deleting the item from list
                 EntryData entryData1=new EntryData(CompletedTask.this);
                 entryData1.delete(arrayList.get(i).Id);

                 //displaying toast for deleted item
                 Toast.makeText(getApplicationContext(),"Item Deleted",Toast.LENGTH_SHORT).show();

                 //updating array list
                 ArrayList<Entry> newEntries=entryData1.getArrayList();
                 CustomAdapter customAdapter1=new CustomAdapter(CompletedTask.this,newEntries);

                 //setting list view to custom adapter
                 listView.setAdapter(customAdapter1);

                //checking if the list is empty
                 if(newEntries.isEmpty())
                 {
                     Toast.makeText(getApplicationContext(),"List is Empty",Toast.LENGTH_SHORT).show();

                     //switching to main Activity
                     Intent intent=new Intent(CompletedTask.this,MainActivity.class);
                     startActivity(intent);
                     finish();
                 }
                 //switching to main Activity
                 Intent intent1=new Intent(CompletedTask.this,MainActivity.class);
                 startActivity(intent1);

                 return true;
             }


         });
          //setting on item clicked of the list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Displaying Toast.
                Toast.makeText(CompletedTask.this,"Please, Long Click to delete the Item.",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
