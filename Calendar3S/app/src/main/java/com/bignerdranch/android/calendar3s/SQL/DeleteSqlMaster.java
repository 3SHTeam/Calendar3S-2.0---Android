package com.bignerdranch.android.calendar3s.SQL;

import android.util.Log;

import com.bignerdranch.android.calendar3s.database.SendToDB;

/**
 * Created by Owner on 2017-09-03.
 */

public class DeleteSqlMaster implements SqlMaster{
    private SendToDB sendToDB;
    private String TagName="";
    private String php = "";
    private String message = "";


    //스케줄 삭제
    public void DeleteMySchedule(String sid){
        this.TagName="DeleteMySchedule";
        this.php="DeleteSchedule.php";
        this.message = "Sid='" + sid + "'";
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }
    public void DeleteScheduleJoin(String sid, String Googleid){
        this.TagName="DeleteScheduleJoin";
        this.php="DeleteScheduleSjoin.php";
        this.message = "Googleid='" + Googleid + "' and Sid='" + sid + "'";
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }

    //그룹삭제
    public void DeleteGroup(String gid){
        this.TagName="DeleteGroup";
        this.php="DeleteGroup.php";
        this.message = gid;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }

    //메세지 삭제
    public void DeleteMessage(String mid) {
        this.TagName="DeleteMessage";
        this.php="DeleteMessage.php";
        this.message = mid;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }

    @Override
    public String runSqlMessage() {
        sendToDB.start();// DB연결 스레드 시작
        try {
            sendToDB.join();// DB연결이 완료될때까지 대기
        } catch (InterruptedException e) {
            Log.e(TagName, this.php);
            Log.e(TagName, this.message);
            Log.e(TagName, e.toString());
        }
        return sendToDB.getResult();
    }
}
