package com.bignerdranch.android.calendar3s.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Owner on 2017-08-29.
 */

public class SelectMakeGroupJson implements JsonMaster{
    private String result = "";
    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    @Override
    public void onPostExecute(String str) {
        String Gid;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.result = null;
                System.out.println("그룹 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Gid = jo.getString("Gid");

                System.out.println(Gid);

                this.result = Gid;
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
