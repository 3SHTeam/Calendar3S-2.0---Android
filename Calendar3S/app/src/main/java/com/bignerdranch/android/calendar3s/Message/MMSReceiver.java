package com.bignerdranch.android.calendar3s.Message;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.bignerdranch.android.calendar3s.MainActivity.context;

public class MMSReceiver extends BroadcastReceiver {
    private final String DEBUG_TAG = getClass().getSimpleName().toString();
    private static final String ACTION_MMS_RECEIVED = "android.provider.Telephony.WAP_PUSH_RECEIVED";
    private static final String MMS_DATA_TYPE = "application/vnd.wap.mms-message";
    private Context MMScontext;
    private String phonenum = "";
    private String message = "";
    private SharedPreferences pref;
    private ScheduleTextChecker checker;

    public MMSReceiver() {
        checker = new ScheduleTextChecker();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();
        String IntentType = intent.getType();

        if (action.equals(ACTION_MMS_RECEIVED) && IntentType.equals(MMS_DATA_TYPE)) {
            MMScontext = context;
            readMMS();
            if (checker.scheduleTextCheck(message)) {
                saveMMSInfo(message, phonenum, MMScontext);
                Log.d("SMSTEST", "saveMMSInfo 실행");
            }
        }
    }


    public String messageFromMms(String mmsId) {
        String messageBody = "";
        String selectionPart = "mid=" + mmsId;
        Uri uriPart = Uri.parse("content://mms/part");
        Cursor cursorPart = MMScontext.getContentResolver().query(uriPart, null, selectionPart, null, null);

        if (cursorPart.moveToFirst()) {

            do {
                String partId = cursorPart.getString(cursorPart.getColumnIndex("_id"));
                String type = cursorPart.getString(cursorPart.getColumnIndex("ct"));
                if ("text/plain".equals(type)) {
                    String data = cursorPart.getString(cursorPart.getColumnIndex("_data"));

                    if (data != null) {
                        // implementation of this method below
                        messageBody += "\n"+getMmsText(partId);
                    }
                    else {
                        messageBody += "\n"+cursorPart.getString(cursorPart.getColumnIndex("text"));
                    }
                }
            } while( cursorPart.moveToNext() );
            cursorPart.close();
        }
        if (TextUtils.isEmpty(messageBody)) return messageBody;
        return messageBody.substring(1);
    }

    public void readMMS() {
        final String[] projection = new String[] {"_id", "ct_t", "date"};
        Uri uri = Uri.parse("content://mms/inbox");
        Cursor cursor = MMScontext.getContentResolver().query(uri, projection, null, null, "date DESC");

        int i = 0;
        if (cursor.moveToFirst()) {
            //do {  //전부 다 가져오기
            String mmsId = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

            // 5) 메시지 본문 가져오기
            String mmsType = cursor.getString(cursor.getColumnIndex("ct_t"));
            // MMS인 것을 한번 더 확인
            if ( mmsType != null && mmsType.startsWith("application/vnd.wap.multipart")) {
                message = messageFromMms(mmsId);
                phonenum = getAddressNumber(mmsId);
                Log.d("ssh", message + " & " + phonenum);
            }
            //} while (cursor.moveToNext());
        }
        cursor.close();
    }

    private String getAddressNumber(String id) {
        String selectionAdd = new String("msg_id=" + id);
        String uriStr = MessageFormat.format("content://mms/{0}/addr", id);
        Uri uriAddress = Uri.parse(uriStr);
        Cursor cAdd = MMScontext.getContentResolver().query(uriAddress, null, selectionAdd, null, null);

        String phoneNum = null;
        if (cAdd.moveToFirst()) {
            do {
                String number = cAdd.getString(cAdd.getColumnIndex("address"));
                if (number != null) {
                    try {
                        Long.parseLong(number.replace("-", ""));
                        phoneNum = number;
                    }
                    catch (NumberFormatException nfe) {
                        if (phoneNum == null) {
                            phoneNum = number;
                        }
                    }
                }
            } while (cAdd.moveToNext());
        }
        if (cAdd != null) {
            cAdd.close();
        }
        return phoneNum;
    }


    private void saveMMSInfo(String str, String phNum, Context context) {
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

    private String getMmsText(String id) {
        Uri partURI = Uri.parse("content://com.sec.mms.provider/message/part" + id);
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = context.getContentResolver().openInputStream(partURI);
            if (is != null) {
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader reader = new BufferedReader(isr);
                String temp = reader.readLine();
                while (temp != null) {
                    sb.append(temp);
                    temp = reader.readLine();
                }
            }
        } catch (IOException e) {}
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        return sb.toString();
    }
}
