package com.bignerdranch.android.calendar3s.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Owner on 2017-08-29.
 */

public class SelectInsertScheduleJson implements JsonMaster {
    private String result = "";
    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    @Override
    public void onPostExecute(String str) {
        String Sid;
        String SMaster;
        String Sname;
        String Place;
        String StartTime;
        String EndTime;

        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.result = null;
                System.out.println("스케줄이 없음!");
                return;
            }

            this.result = "0";
            JSONArray ja = root.getJSONArray("result");
            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Sid = jo.getString("Sid");
                SMaster = jo.getString("SMaster");
                Sname = jo.getString("Sname");
                Place = jo.getString("Place");
                StartTime = jo.getString("StartTime");
                EndTime = jo.getString("EndTime");

                System.out.println(Sid + " , " + SMaster + " , " + Sname
                        + " , " + Place + " , " + StartTime + " , " + EndTime);

                this.result = Sid;
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
