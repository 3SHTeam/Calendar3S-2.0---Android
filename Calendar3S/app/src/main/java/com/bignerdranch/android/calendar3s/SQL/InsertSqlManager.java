package com.bignerdranch.android.calendar3s.SQL;

import android.util.Log;

import com.bignerdranch.android.calendar3s.database.SendToDB;


/**
 * Created by Owner on 2017-08-29.
 */

public class InsertSqlManager implements SqlMaster{
    private SendToDB sendToDB;
    private String TagName="";
    private String php = "";
    private String message = "";


    //회원가입 ( 회원추가, 태그추가 )
    public void AddFirestTag(String data) {
        this.TagName = "AddFirstTag";
        this.php = "InsertTag.php";
        this.message = "'" + data + "','" + data + "','" + "#82B926"
                +"','맑은고딕','15','NULL'";
        sendToDB = new SendToDB(php,message);
        runSqlMessage();
    }

    public void SighupUser(String message){
        this.TagName = "SighupUser";
        this.php = "InsertUser.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }


    //스케줄
    public void insertScheduleDB(String message) {
        this.TagName="insertScheduleDB";
        this.php="InsertSchedule.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }
    public void joinScheduleDB(String message) {
        this.TagName="joinScheduleDB";
        this.php="JoinSchedule.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }
    public void joinGroupSchedule(String message) {
        this.TagName="joinGroupSchedule";
        this.php="JoinGroupSchedule.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }

    //그룹
    public void InsertGroupTag(String message){
        this.TagName="InsertGroupTag";
        this.php="InsertTag.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }
    public void JoinGroup(String message){
        this.TagName="JoinGroup";
        this.php="JoinGroup.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }
    public void InsertGroup(String message){
        this.TagName="InsertGroup";
        this.php="InsertGroup.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }

    //메세지
    public void InsertMessage(String message){
        this.TagName="InsertMessage";
        this.php="InsertMessage.php";
        this.message = message;
        sendToDB = new SendToDB(php, message);
        runSqlMessage();
    }


    //태그
    public void InsertTag(String tagname, String color) {
        this.TagName="InsertTag";
        this.php="";
        this.message = message;
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
