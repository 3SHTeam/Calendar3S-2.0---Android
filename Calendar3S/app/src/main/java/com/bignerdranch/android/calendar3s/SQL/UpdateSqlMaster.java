package com.bignerdranch.android.calendar3s.SQL;

import android.util.Log;

import com.bignerdranch.android.calendar3s.database.UpdateToDB;

/**
 * Created by Owner on 2017-09-03.
 */

public class UpdateSqlMaster implements SqlMaster{
    private UpdateToDB updateToDB;
    private String TagName="";
    private String php = "";
    private String message = "";
    private String id = "";

    //스케줄 업데이트
    public void UpdateSchedule(String eventId, String eventTitle, String startTimedB, String endTimedB, String location) {
        this.TagName="UpdateSchedule";
        this.php="UpdateSchedule.php";
        this.message = "Sname='" + eventTitle + "',Place='" + location + "',StartTime='"
                + startTimedB + "',EndTime='" + endTimedB + "'";
        this.id = eventId;
        updateToDB = new UpdateToDB(php,message,id);
        runSqlMessage();
    }
    public void UpdateScheduleJoin(String eventId, String tagId, String Googleid) {
        this.TagName="UpdateScheduleJoin";
        this.php="UpdateScheduleJoin.php";
        this.message = "Tagid='" + tagId + "'";
        this.id = "Googleid='" + Googleid + "'and Sid='" + eventId + "'";
        updateToDB = new UpdateToDB(php,message,id);
        runSqlMessage();
    }

    @Override
    public String runSqlMessage() {
        updateToDB.start();// DB연결 스레드 시작
        try {
            updateToDB.join();// DB연결이 완료될때까지 대기
        } catch (InterruptedException e) {
            Log.e(TagName, this.php);
            Log.e(TagName, this.message);
            Log.e(TagName, e.toString());
        }
        return updateToDB.getResult();
    }
}