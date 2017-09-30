package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.TagData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SelectMyTagJson implements JsonMaster {
    private ArrayList<TagData> tags = new ArrayList<TagData>();
    public ArrayList<TagData> getTags() {return tags;}
    public void setTags(ArrayList<TagData> tags) {this.tags = tags;}

    @Override
    public void onPostExecute(String str) {
        String Tagid;
        String Tag_name;
        String Color;
        String Font;
        String Size;
        String Gid;

        TagData tag;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.tags = null;
                System.out.println("태그가 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Tagid = jo.getString("Tagid");
                Tag_name= jo.getString("Tag_name");
                Color = jo.getString("Color");
                Font = jo.getString("Font");
                Size = jo.getString("Size");
                Gid = jo.getString("Gid");

                System.out.println(Tagid + " , " + Tag_name + " , " + Color
                        + " , " + Font + " , " + Size + " , " + Gid);

                tag = new TagData(Tagid, Tag_name, Color, /*Font, Size,*/ Gid);
                this.tags.add(tag);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
