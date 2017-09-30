package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.GroupData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Owner on 2017-08-29.
 */

public class SelectMyGroupJson implements JsonMaster {
    private ArrayList<GroupData> Groups =new ArrayList<GroupData>();
    public ArrayList<GroupData> getGroups() {return Groups;}
    public void setGroups(ArrayList<GroupData> groups) {Groups = groups;}

    @Override
    public void onPostExecute(String str) {
        String gid;
        String name;
        String comment;
        String GMaster;

        GroupData group;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.Groups = null;
                System.out.println("그룹 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                gid = jo.getString("Gid");
                name= jo.getString("Group_name");
                comment = jo.getString("Group_comment");
                GMaster = jo.getString("GMaster");

                System.out.println(gid + " , " + name + " , " + comment
                        + " , " + GMaster);

                group= new GroupData(gid, name, comment, GMaster);
                this.Groups.add(group);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
