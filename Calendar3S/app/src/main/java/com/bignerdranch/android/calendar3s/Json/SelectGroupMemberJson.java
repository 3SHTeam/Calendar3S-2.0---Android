package com.bignerdranch.android.calendar3s.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectGroupMemberJson implements JsonMaster {
    private ArrayList<String> userIds_Arr = new ArrayList<String>();// 회원 아이디
    public ArrayList<String> getUserIds_Arr() {return userIds_Arr;}
    public void setUserIds_Arr(ArrayList<String> userIds_Arr) {this.userIds_Arr = userIds_Arr;}

    @Override
    public void onPostExecute(String str) {
        String GroupMemberIds;

        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.userIds_Arr = null;
                System.out.println("그룹 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                GroupMemberIds = jo.getString("Googleid");

                System.out.println(GroupMemberIds);

                this.userIds_Arr.add(GroupMemberIds);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
