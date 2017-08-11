package com.example.shramona.uihackathon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

public class Calen extends Activity implements View.OnClickListener {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private Button button;
    private Button done;
    private String text1,text2,empid;
    private String[] Reason = {"Vertical Bar Chart","Written feedback"};
    private int year, month, day, d1, d2, m1, m2, y1, y2, flag;
     private Spinner dropdown1;
    private TextView textView;
    public static final String JSON_WT = "http://shramocse.000webhostapp.com/FeedHac.php";
    EditText Empid,Wt;
    public ListView lv;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calen);

        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));*/

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateView=(TextView) findViewById(R.id.textView);
        dropdown1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> Adapter1 = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,Reason);
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown1.setAdapter(Adapter1);
        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    public void setDateFrom(View view) {
        dateView=(TextView) findViewById(R.id.textView1);
        button=(Button) findViewById(R.id.button1);
        switch (view.getId())
        {
            case R.id.button1: flag=1;
                break;
        }
        showDialog(999);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2+1, arg3);
        }
    };
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        switch(flag){
            case 1: y1=year; m1=month; d1=day;
                break;
            default:
                // Toast.makeText(this,"Enter Inputs", Toast.LENGTH_SHORT).show();
        }

    }

    public void GO(View v)
    {
        if(d1==0){
            Toast.makeText(getApplicationContext(),"Select a Date",Toast.LENGTH_SHORT).show();
        }
         else
        {
          text1 = dropdown1.getSelectedItem().toString();
        //text2 = dropdown2.getSelectedItem().toString();
        String sy = Integer.toString(y1);
        String sm = Integer.toString(m1);
        String sd = Integer.toString(d1);
        Toast.makeText(this,sd, Toast.LENGTH_SHORT).show();
        // String empid=textView.getText().toString().trim().toLowerCase();
          if((text1=="Vertical Bar Chart"||text1=="Written feedback" || text1=="")) {
                AlertDialog alertDialo = new AlertDialog.Builder(Calen.this).create();
                alertDialo.setTitle("Alert");
               // alertDialo.setMessage("This Leaves Can Only Be Full Day");

                alertDialo.setCanceledOnTouchOutside(true);
                alertDialo.show();
            }
        }

    }



    @Override
    public void onClick(View v) {
        text1 = dropdown1.getSelectedItem().toString();
        if(text1 == "Written feedback") {
            //Toast.makeText(getApplicationContext(), "Hey there! Waito babe/dude... Work on progress", Toast.LENGTH_LONG).show();
            pd = ProgressDialog.show(Calen.this, "", "Loading, please wait!");
            lv = (ListView) findViewById(R.id.lv);
            getData();
        }

        if(text1=="Vertical Bar Chart"){
            Intent intent = new Intent(Calen.this, verticalBarChart.class);
            startActivity(intent);
        }

    }
     /* List view*/

    private void getData() {
        StringRequest sr = new StringRequest(JSON_WT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        sshowDetails(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(Calen.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }

    private void sshowDetails(String json){
        ParseDetailsf pdf = new ParseDetailsf(json);
         pdf.parseDetails();

        Checkf cl = new Checkf(Calen.this,Empid,ParseDetailsf.Wt);
        lv.setAdapter(cl);
    }
/* List view ends*/
}
