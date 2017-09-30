package com.bignerdranch.android.calendar3s.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Owner on 2017-08-29.
 */

public class SelectMyGroupTagJson implements JsonMaster {
    private String result = "";
    public String getResult() {return result;}
    public void setResult(String result) {this.result = result;}

    @Override
    public void onPostExecute(String str) {
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.result = "";
                System.out.println("그룹태그가 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                this.result = jo.getString("Tagid");
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
