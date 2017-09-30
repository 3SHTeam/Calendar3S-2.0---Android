package com.bignerdranch.android.calendar3s.Message;

import android.app.Activity;
import android.app.Service;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.bignerdranch.android.calendar3s.MainActivity.context;

public class MyService extends Service {
    public static SharedPreferences pref;
    private ScheduleTextChecker checker;

    public MyService() {
        checker = new ScheduleTextChecker();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final ClipboardManager clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE); //클립보드 간단 예시
        clipboard.addPrimaryClipChangedListener( new ClipboardManager.OnPrimaryClipChangedListener() {
            public void onPrimaryClipChanged() {
                String a = clipboard.getText().toString();

                if (checker.scheduleTextCheck(a)) {
                    saveMMSInfo(a, "Clipboard", context);
                }


            }
        });
        return START_STICKY;
    }

    private void saveMMSInfo(String str, String phNum, Context context) {
        pref = this.getApplicationContext().getSharedPreferences("acceptSMS", Activity.MODE_PRIVATE);
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
        Log.d("ssh", prevStr);
        editor.putString("SMS", prevStr);
        editor.commit();
    }
}
