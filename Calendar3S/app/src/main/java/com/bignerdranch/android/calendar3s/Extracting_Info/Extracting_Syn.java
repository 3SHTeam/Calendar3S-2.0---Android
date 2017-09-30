package com.bignerdranch.android.calendar3s.Extracting_Info;

/**
 * Created by Owner on 2017-09-15.
 */

public class Extracting_Syn {
    private Extracting_Date ex_Date;
    private Extracting_Time ex_Time;
    private Extracting_Place ex_Place;
    private String date;
    private String time;
    private String place;

    public Extracting_Syn(String txt, String msg_date){
        ex_Date = new Extracting_Date();
        ex_Time = new Extracting_Time();
        ex_Place = new Extracting_Place();
        ex_Date.Date_Recognition(txt, msg_date);
        ex_Time.Time_Recognition(txt);
        ex_Place.Place_Recognition(txt);

        date = ex_Date.get_Date();
        time = ex_Time.get_Time();
        place = ex_Place.get_Place();
    }

    public String get_Date(){
        return date;
    }

    public String get_Time(){
        return time;
    }

    public String get_Place(){
        return place;
    }

}