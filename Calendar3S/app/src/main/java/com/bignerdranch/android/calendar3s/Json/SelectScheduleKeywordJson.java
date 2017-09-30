package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.KeywordData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-09-15.
 */

public class SelectScheduleKeywordJson implements JsonMaster {
    private ArrayList<KeywordData> nnkeywords = new ArrayList<KeywordData>();
    private ArrayList<KeywordData> vvkeywords = new ArrayList<KeywordData>();
    public ArrayList<KeywordData> getnnkeywords() {return nnkeywords;}
    public ArrayList<KeywordData> getvvkeywords() {return vvkeywords;}

    @Override
    public void onPostExecute(String str) {
        KeywordData keyword;
        try {
            JSONObject root = new JSONObject(str);
            if (root.get("rownum").equals("0")) {
                this.vvkeywords = null;
                this.nnkeywords = null;
                System.out.println("메세지가 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                keyword = new KeywordData(
                        jo.getString("num"),
                        jo.getString("text"),
                        jo.getString("type")
                );
                if(keyword.getData(2).equals("1"))
                    this.nnkeywords.add(keyword);
                if(keyword.getData(2).equals("2"))
                    this.vvkeywords.add(keyword);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
