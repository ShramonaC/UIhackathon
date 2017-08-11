package com.example.shramona.uihackathon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.shramona.uihackathon.ParseDetailsf.Empid;

public class Checkf extends ArrayAdapter<String>{
    private String[] empid;
    private String[] Wt;
    private Activity context;

    public Checkf(Calen context, EditText empid, String[] Wt) {
        super(context, R.layout.listcheckf, Empid);
        this.context = context;
        this.empid = Empid;
        this.Wt = Wt ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listcheckf, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.empid);
        TextView textViewWrittn = (TextView) listViewItem.findViewById(R.id.writtn);

        textViewId.setText(empid[position]);
        textViewWrittn.setText(Wt[position]);


        return listViewItem;

    }
}
