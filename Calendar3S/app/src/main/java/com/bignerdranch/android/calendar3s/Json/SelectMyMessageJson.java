package com.bignerdranch.android.calendar3s.Json;

import com.bignerdranch.android.calendar3s.data.MessageData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Owner on 2017-08-29.
 */

public class SelectMyMessageJson implements JsonMaster {
    public ArrayList<MessageData> messages = new ArrayList<MessageData>();
    public ArrayList<MessageData> getMessages() {return messages;}
    public void setMessages(ArrayList<MessageData> messages) {this.messages = messages;}

    @Override
    public void onPostExecute(String str) {
        String Mid;
        String sender;
        String receiver;
        String type;
        String message;
        String Gid;
        String Gname;

        MessageData mdata;
        try{
            JSONObject root = new JSONObject(str);
            if(root.get("rownum").equals("0")) {
                this.messages = null;
                System.out.println("메세지가 없음!");
                return;
            }

            JSONArray ja = root.getJSONArray("result");

            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Mid = jo.getString("Mid");
                sender= jo.getString("sender");
                receiver = jo.getString("receiver");
                type = jo.getString("type");
                message = jo.getString("message");
                Gid = jo.getString("Gid");
                Gname = jo.getString("Gname");

                System.out.println(Mid + " , " + sender + " , " + receiver
                        + " , " + type + " , " + message + " , " + Gid + " , " + Gname);

                mdata= new MessageData(Mid, sender, receiver, type, message, Gid, Gname);
                this.messages.add(mdata);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
