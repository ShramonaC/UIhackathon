package com.example.shramona.uihackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class verticalBarChart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String JSON_WT = "http://shramocse.000webhostapp.com/GraHac.php";
    public static final String KEY_VAL = "Val";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_bar_chart);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Work Environment");
        categories.add("Fooding");
        categories.add("Work Culture");
        categories.add("Flexibility");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        final String Val= item.trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_WT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(verticalBarChart.this, response, Toast.LENGTH_LONG).show();
                        Log.e(response,"here i am");
                        getData();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(verticalBarChart.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_VAL, Val);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void getData() {

        StringRequest sr = new StringRequest(JSON_WT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showDetails(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(verticalBarChart.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }

    private void showDetails(String json){
        ParseDetailsg pdg = new ParseDetailsg(json);
        pdg.parseDetailsg();


        //Checkf cl = new Checkf(Calen.this,Empid,ParseDetailsf.Wt);
        //lv.setAdapter(cl);
    }
    /* List view ends*/
    public void showgraph(){

        BarChart chart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> BarEntry = new ArrayList<>();
        BarEntry.add(new BarEntry(9, 0));
        BarEntry.add(new BarEntry(4, 1));
        BarEntry.add(new BarEntry(6, 2));
        BarEntry.add(new BarEntry(8, 3));
        BarEntry.add(new BarEntry(7, 4));
        BarEntry.add(new BarEntry(3, 5));
        BarEntry.add(new BarEntry(4, 6));
        BarEntry.add(new BarEntry(3f, 7));
        BarEntry.add(new BarEntry(1f, 8));
        BarEntry.add(new BarEntry(6f, 9));

        BarDataSet dataSet = new BarDataSet(BarEntry, "Daily feedbacks");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        chart.getAxisLeft().setAxisMaxValue(100f);

        BarData data = new BarData(labels, dataSet);

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        chart.setDescription("Feedback");
    }
}
