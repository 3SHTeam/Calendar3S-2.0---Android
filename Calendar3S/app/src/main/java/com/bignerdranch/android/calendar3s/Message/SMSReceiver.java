package com.bignerdranch.android.calendar3s.Message;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SMSReceiver extends BroadcastReceiver {

    private String phNum = "";
    private String str = "";
    private SharedPreferences pref;
    public static ArrayList<HashMap<String, String>> BCsmsList;
    private ScheduleTextChecker checker;

    public SMSReceiver() {
        checker = new ScheduleTextChecker();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            pref = context.getSharedPreferences("acceptSMS", Context.MODE_PRIVATE);

            BCsmsList = new ArrayList<HashMap<String, String>>();
            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            for (int i = 0; i < messages.length; i++) {
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
                phNum = smsMessage[i].getOriginatingAddress();
            }

            //Toast.makeText(context,"RRR", Toast.LENGTH_LONG).show();
            str = smsMessage[0].getMessageBody().toString();
            Log.d("ssh", str);

            if (checker.scheduleTextCheck(str)) {
                saveSMSInfo(str, phNum, context);
                Log.d("SMSTEST", "saveInfo 실행");
            }

        }
    }


    private void saveSMSInfo(String str, String phNum, Context context) {
        pref = context.getSharedPreferences("acceptSMS", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String  prevStr= pref.getString("SMS", "");
        String input_date;

        long now = System.currentTimeMillis();  //현재 날짜 구하기
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd  HH:mm", Locale.KOREA);
        input_date = sdfNow.format(date);

        String  currStr= str +"@,@,@,@"+phNum + "  " + input_date;
        if(prevStr == ""){
            prevStr= "@@@@@@@"+currStr;
        }else{
            prevStr= prevStr +"@@@@@@@"+currStr;
        }
        editor.putString("SMS", prevStr);
        editor.commit();
    }
}
