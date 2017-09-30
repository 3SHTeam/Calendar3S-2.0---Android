package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.EventData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectMyScheduleJson implements JsonMaster {
    private ArrayList<EventData> events = new ArrayList<EventData>();
    public ArrayList<EventData> getEvents() {return events;}
    public void setEvents(ArrayList<EventData> events) {this.events = events;}

    @Override
    public void onPostExecute(String str) {
        String Sid;
        String SMaster;
        String Sname;
        String Place;
        String StartTime;
        String EndTime;
        String Gid;
        String GoogleSid;
        String TagId;

        EventData event;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.events = null;
                System.out.println("태그가 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");
            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Sid = jo.getString("Sid");
                SMaster = jo.getString("SMaster");
                Sname = jo.getString("Sname");
                Place = jo.getString("Place");
                StartTime = jo.getString("StartTime");
                EndTime = jo.getString("EndTime");
                Gid = jo.getString("Gid");
                GoogleSid = jo.getString("GoogleSid");
                TagId = jo.getString("Tagid");

                System.out.println(Sid + " , " + SMaster + " , " + Sname
                        + " , " + Place + " , " + StartTime + " , " + EndTime
                        + " , " + Gid + " , " + GoogleSid + " , " + TagId);

                event = new EventData(Sid,SMaster,Sname,Place,StartTime,EndTime,TagId,GoogleSid,Gid);
                this.events.add(event);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
