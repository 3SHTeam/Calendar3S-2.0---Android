package com.bignerdranch.android.calendar3s.data;

/**
 * Created by Owner on 2017-09-15.
 */

public class KeywordData implements DataInfo{

    private String[] uData = new String[3];

    public KeywordData(String id, String text, String type){
        setData(0,id);
        setData(1,text);
        setData(2,type);
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
        return null;
    }
    @Override
    public void showData() {}

}
