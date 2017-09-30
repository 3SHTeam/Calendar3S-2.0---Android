package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.UserData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SelectMyProfileJson implements JsonMaster {
    private UserData user;
    public UserData getUser() {return user;}
    public void setUser(UserData user) {this.user = user;}

    @Override
    public void onPostExecute(String str) {
        String Googleid;
        String pw;
        String name;
        String gender;
        String nickname;
        String birth;
        String phone;
        String comment;
        String FBuId;
        String FBToken;

        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.user = null;
                System.out.println("로그인 실패!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Googleid = jo.getString("Googleid");
                pw = jo.getString("Googlepw");
                name= jo.getString("name");
                gender = jo.getString("gender");
                nickname = jo.getString("nickname");
                birth = jo.getString("birth");
                phone = jo.getString("phone");
                comment = jo.getString("comment");
                FBuId = jo.getString("FBuId");
                FBToken = jo.getString("FBToken");

                System.out.println(Googleid + " , " + name + " , " + gender
                        + " , " + nickname + " , " + birth + " , " + phone
                        + " , " + comment + " , " + FBuId + " , " + FBToken );

                this.user = new UserData(Googleid, name, gender, nickname, birth, phone, comment, FBuId, FBToken);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
