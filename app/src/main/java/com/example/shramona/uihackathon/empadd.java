package com.example.shramona.uihackathon;


         import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class empadd extends AppCompatActivity {
    public static final String JSON_URL = "http://shramocse.000webhostapp.com/storeHac.php";
    int k=0;
    EditText Empid,Name,Desig;
    private ListView listView;
    ProgressDialog pd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empadd);

        pd = ProgressDialog.show(empadd.this, "", "Loading, please wait!");

        listView = (ListView) findViewById(R.id.lView);

        getData();

    }

    /* List view*/

    private void getData() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        showDetails(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(empadd.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDetails(String json){
        ParseDetails pd = new ParseDetails(json);
        pd.parseDetails();
       // Check adapter = new Check(empadd.this, Empid,ParseDetails.Name,ParseDetails.Desig);
        Check adapter = new Check(empadd.this,Empid,ParseDetails.Name,ParseDetails.Desig);
        listView.setAdapter(adapter);
    }
/* List view ends*/

    @Override
    public void onBackPressed()
    {
        Log.e("My Tags", "onBackPressed");
        k++;
        if(k == 1)
        {
            Toast.makeText(empadd.this, "Please press again to exit.", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.notify_id:
               Intent i = new Intent(getApplicationContext(), Calen.class);
                startActivity(i);
                return true;
            case R.id.btnLogout:
                Intent in = new Intent(getApplicationContext(), empadd.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(in);
                return true;
            case R.id.addcmpy:
                Intent intent = new Intent(getApplicationContext(), Save.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}




