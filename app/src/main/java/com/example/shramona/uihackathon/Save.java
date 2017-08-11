package com.example.shramona.uihackathon;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Save extends AppCompatActivity {

   // public TextView empid1,name1,desig1;
    public EditText empid,name,desig;
    int k=0;
    public static final String JSON_URL_Add = "http://shramocse.000webhostapp.com/addHac.php";
    public static final String KEY_EMPID = "Empid";
    public static final String KEY_NAME = "Name";
    public static final String KEY_DESIG = "Desig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        try {

            empid = (EditText) findViewById(R.id.empid);
            name= (EditText) findViewById(R.id.name);
            desig= (EditText) findViewById(R.id.desig);

            /* empid1 = (TextView) findViewById(R.id.empid1);
            name1= (TextView) findViewById(R.id.name1);
            desig1= (TextView) findViewById(R.id.desig1); */

        } catch (ActivityNotFoundException a) {
        }
    }

    public void ok(View v) {
        final String Empid = empid.getText().toString().trim();
        final String Name = name.getText().toString().trim();
        final String Desig = desig.getText().toString().trim();
        if (Empid.length() > 0 && Name.length()>0 && Desig.length()>0 ) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_Add,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Save.this, response, Toast.LENGTH_LONG).show();
                            Log.e(response,"here i am");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Save.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_EMPID, Empid);
                    params.put(KEY_NAME, Name);
                    params.put(KEY_DESIG, Desig);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), empadd.class);
        startActivity(i);
    }
}
