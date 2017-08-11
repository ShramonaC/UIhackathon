package com.example.shramona.uihackathon;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class ParseDetails {
    public static String[] Empid;
    public static String[] Name;
    public static String[] Desig;

    public static final String JSON_URL_Add = "result";
    public static final String KEY_EMPID = "Empid";
    public static final String KEY_NAME = "Name";
    public static final String KEY_DESIG = "Desig";

    private JSONArray company = null;

    private String json;

    public ParseDetails(String json){
        this.json = json;
    }

    protected void parseDetails(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            company = jsonObject.getJSONArray(JSON_URL_Add);

            Empid= new String[company.length()];
            Name = new String[company.length()];
            Desig = new String[company.length()];

            for(int i=0;i<company.length();i++){
                JSONObject jo = company.getJSONObject(i);
                Empid[i] = jo.getString(KEY_EMPID);
                Name[i] = jo.getString(KEY_NAME);
                Desig[i] = jo.getString(KEY_DESIG);

                String x=Desig[0];
                Log.i(x,"he");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

