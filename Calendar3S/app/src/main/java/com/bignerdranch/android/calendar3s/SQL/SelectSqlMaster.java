package com.bignerdranch.android.calendar3s.SQL;

import android.util.Log;

import com.bignerdranch.android.calendar3s.database.SendToDB;


/**
 * Created by Owner on 2017-09-03.
 */

public class SelectSqlMaster implements SqlMaster{
    private SendToDB sendToDB;
    private String TagName="";
    private String php = "";
    private String message = "";


    //회원가입 - 중복된 유저 확인
    public void CheckUser(String id) {
        this.TagName = "CheckUser";
        this.message = id;
        this.php = "SelectMyProfile.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //로그인 - id,pw확인
    public void signinDB(String id, String pw) {
        this.TagName="signinDB";
        this.message = "where Googleid = '" + id + "' and Googlepw = '" + pw + "'";
        this.php = "SelectMyProfile.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 스케줄
    public void freshMySchedule(String id) {
        this.TagName="freshMySchedule";
        this.message = id;
        this.php = "SelectMySchedule.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 태그
    public void freshTag(String id) {
        this.TagName="freshTag";
        this.message = id;
        this.php = "SelectMyTag.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 그룹
    public void freshGroup(String id) {
        this.TagName="freshGroup";
        this.message = id;
        this.php = "SelectMyGroup.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 그룹 멤버
    public void selectGroupMember(String id) {
        this.TagName="selectGroupMember";
        this.message = id;
        this.php = "SelectGroupMember.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 그룹태그
    public void selectMyGroupTag(String id) {
        this.TagName="selectMyGroupTag";
        this.message = id;
        this.php = "SelectMyGroupTag.php";
        this.sendToDB = new SendToDB(php, message);
    }
    //새로고침 - 초대메세지
    public void freshMessage(String id) {
        this.TagName="freshMessage";
        this.message = "receiver='" + id + "'";
        this.php = "SelectMyMessage.php";
        this.sendToDB = new SendToDB(php, message);
    }


    //그룹추가
    public void selectMakeGroup(String id, String groupName){
        this.TagName="selectMakeGroup";
        this.message = "GMaster='" + id + "' and Group_name='" + groupName + "'";
        this.php = "SelectMakeGroup.php";
        this.sendToDB = new SendToDB(php, message);
    }

    //스케줄 추가
    public void SelectInsertSchedule(String message) {
        this.TagName="SelectInsertSchedule";
        this.message = message;
        this.php = "SelectSchedule.php";
        this.sendToDB = new SendToDB(php, message);
    }

    //스케줄 키워드 가져오기
    public void selectKeywords() {
        this.TagName="selectKeywords";
        this.message = "";
        this.php = "SelectKeywords.php";
        this.sendToDB = new SendToDB(php, message);
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

