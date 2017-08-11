package com.example.shramona.uihackathon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.shramona.uihackathon.ParseDetails.Empid;

public class Check extends ArrayAdapter<String>{
    private String[] empid;
    private String[] Name;
    private String[] Desig;
    private Activity context;

    public Check(empadd context, EditText empid, String[] name, String[] desig) {
        super(context, R.layout.listcheck, Empid);
        this.context = context;
        this.empid = Empid;
        this.Name =name ;
        this.Desig = desig;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listcheck, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.empid);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewdesig = (TextView) listViewItem.findViewById(R.id.desig);

        textViewId.setText(empid[position]);
        textViewName.setText(Name[position]);
        textViewdesig.setText(Desig[position]);

        return listViewItem;

    }
}
