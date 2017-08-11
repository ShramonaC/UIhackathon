package com.example.shramona.uihackathon;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class ParseDetailsf {
    public static String[] Empid;
    public static String[] Wt;
    public static final String JSON_WT = "result";
    public static final String KEY_EMPID = "Empid";
    public static final String WT = "Wt";

    private JSONArray company = null;

    private String json;

    public ParseDetailsf(String json){
        this.json = json;
    }

    protected void parseDetails(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            company = jsonObject.getJSONArray(JSON_WT);

            Empid= new String[company.length()];
            Wt = new String[company.length()];

            for(int i=0;i<company.length();i++){
                JSONObject jo = company.getJSONObject(i);
                Empid[i] = jo.getString(KEY_EMPID);
                Wt[i] = jo.getString(WT);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

