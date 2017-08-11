package com.example.shramona.uihackathon;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class ParseDetailsg {
    public static String[] graph;

    public static final String JSON_WTO = "result";
    public static final String KEY_GET = "Val";

    private JSONArray company = null;

    private String json;

    public ParseDetailsg(String json){
        this.json = json;
    }

    protected void parseDetailsg(){
        JSONObject jsonObject=null;

        try {
            jsonObject = new JSONObject(json);

            company = jsonObject.getJSONArray(JSON_WTO);

            graph= new String[company.length()];

            for(int i=0;i<company.length();i++){
                JSONObject jo = company.getJSONObject(i);
                graph[i] = jo.getString(KEY_GET);

                String x= graph[0];
                Log.e(x,"hey");
                //Toast.makeText(ParseDetailsg.this,json,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

