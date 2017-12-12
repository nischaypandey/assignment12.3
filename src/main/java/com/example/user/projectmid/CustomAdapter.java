package com.example.user.projectmid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 10-11-2017.
 */
//custom Adapter class which is extending BaseAdapter
public class CustomAdapter extends BaseAdapter {

    //initialising arrayList,content
    ArrayList<Entry> arrayList;
    Context context;

     //constructor used for setting Context and arrayList
    public CustomAdapter(Context mainActivity, ArrayList<Entry> initentries) {
        arrayList=initentries;
        context=mainActivity;
    }

     //making getter() and setter() method to number of elements and array elements
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    //getView method
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

          //inflating row Layout
        view= LayoutInflater.from(context).inflate(R.layout.row,null);

        //setting dates
        TextView upDate=view.findViewById(R.id.upDate);
        TextView downDate=view.findViewById(R.id.downDate);

        //setting title
        TextView title=view.findViewById(R.id.titleR);
        //setting description
        TextView desc=view.findViewById(R.id.decriptionR);
        //setting completed image
        ImageView status=view.findViewById(R.id.completionR);

        //setting items of the UI
        upDate.setText(arrayList.get(i).Date);
        downDate.setText(arrayList.get(i).Date);
        title.setText(arrayList.get(i).title);
        desc.setText(arrayList.get(i).Desc);

        //checking if task is completed
        if(arrayList.get(i).status==1)

             //if completed setting completed image
            status.setImageResource(R.drawable.completed);
        else
            //else setting uncomplted image
            status.setImageResource(R.drawable.like);

        return view;
    }
}
