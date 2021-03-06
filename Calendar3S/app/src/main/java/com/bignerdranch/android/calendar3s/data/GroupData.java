package com.bignerdranch.android.calendar3s.data;

import com.bignerdranch.android.calendar3s.Json.SelectGroupMemberJson;
import com.bignerdranch.android.calendar3s.SQL.SelectSqlMaster;

import java.util.ArrayList;

/**
 * Created by ieem5 on 2017-05-08.
 */

public class GroupData implements DataInfo {
    private String[] uData = new String[4];
    private ArrayList<String> userIds_Arr;// 회원 아이디

    public GroupData() {

    }
    public GroupData(String GId, String name, String comment, String gmaster) {
        setData(0, GId);
        setData(1, name);
        setData(2, comment);
        setData(3, gmaster);
    }


    @Override
    public String[] getData() {
        return uData;
    }

    @Override
    public String getData(int index) {
        return uData[index];
    }

    @Override
    public void setData(int index, String data) {
        uData[index] = data;
    }

    @Override
    public void setData(String[] uData) {
        this.uData = uData;
    }

    @Override
    public String getSendSQLString() {
        String sql = "'" + uData[1] + "','" + uData[2] + "','" + uData[3] + "'";
        return sql;
    }

    @Override
    public void showData() {
        System.out.println("GId : " + uData[0]);
        System.out.println("GroupName : " + uData[1]);
        System.out.println("comment : " + uData[2]);
        System.out.println("gmaster : " + uData[3]);
    }

    public ArrayList<String> getUserIds_Arr() {
        return userIds_Arr;
    }

    public void setUserIds_Arr() {
         /* DB에서 스케줄 가져오기 */
        SelectSqlMaster sqlMaster = new SelectSqlMaster();
        sqlMaster.selectGroupMember(getData(0));

        SelectGroupMemberJson json = new SelectGroupMemberJson();
        json.onPostExecute(sqlMaster.runSqlMessage());

        userIds_Arr = json.getUserIds_Arr();
    }


    @Override
    public String toString() {
        String str = "GId: "+uData[0]+" name: "+uData[1]+" comment : "+uData[2]+
                "  gmaster :  "+uData[3]+"user_ids : "+getUserIds_Arr().toString();
        return str;
    }


}
