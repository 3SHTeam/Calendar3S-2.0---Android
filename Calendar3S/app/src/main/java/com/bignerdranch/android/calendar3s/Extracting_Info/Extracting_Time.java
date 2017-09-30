package com.bignerdranch.android.calendar3s.Extracting_Info;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Owner on 2017-09-15.
 */

public class Extracting_Time {
    private String time;

    public Extracting_Time(){
        time = "I";
    }

    public String get_Time(){
        return time;
    }

    public void set_Time(String group){
        time = group;
    }

    public void Time_Recognition(String txt){
        int hour;
        String s_hour;
        Pattern TimeP1 = Pattern.compile("(오전|아침|오후|[AaPp][Mm]|낮|저녁|)(\\s*[0-9]|\\s*[0-1][0-9]|\\s*2[0-3])[시:](\\s*[0-9]|\\s*[0-5][0-9])분");
        Pattern TimeP2 = Pattern.compile("(오전|아침|오후|[AaPp][Mm]|낮|저녁|)(\\s*[0-9]|\\s*[0-1][0-9]|\\s*2[0-3])시");

        Matcher matcher = TimeP1.matcher(txt);
        if(matcher.find()){
            if(matcher.group(1).equals("오전") || matcher.group(1).equals("아침") || matcher.group(1).equals("am") ||
                    matcher.group(1).equals("Am") || matcher.group(1).equals("AM") || matcher.group(1).equals("")){
                hour = Integer.valueOf(matcher.group(2).trim()).intValue();
                s_hour = String.format("%02d", hour);
                set_Time(s_hour+":"+matcher.group(3).trim());
                return;
            }
            else if(matcher.group(1).equals("pm") || matcher.group(1).equals("Pm") || matcher.group(1).equals("PM") ||
                    matcher.group(1).equals("오후") || matcher.group(1).equals("낮") || matcher.group(1).equals("저녁")){
                hour = Integer.valueOf(matcher.group(2).trim()).intValue() + 12;
                set_Time(hour + ":" + matcher.group(3).trim());
                return;
            }
        }

        matcher = TimeP2.matcher(txt);
        if(matcher.find()){
            if(matcher.group(1).equals("오전") || matcher.group(1).equals("아침") || matcher.group(1).equals("am") ||
                    matcher.group(1).equals("Am") || matcher.group(1).equals("AM") || matcher.group(1).equals("")){
                hour = Integer.valueOf(matcher.group(2).trim()).intValue();
                s_hour = String.format("%02d", hour);
                set_Time(s_hour+":"+"00");
                return;
            }
            else if(matcher.group(1).equals("pm") || matcher.group(1).equals("Pm") || matcher.group(1).equals("PM") ||
                    matcher.group(1).equals("오후") || matcher.group(1).equals("낮") || matcher.group(1).equals("저녁")){
                hour = Integer.valueOf(matcher.group(2).trim()).intValue() + 12;
                set_Time(hour+":"+"00");
                return;
            }
        }

        if(txt.contains("아침")){
            set_Time("07:00");
            return;
        }
        if(txt.contains("정오") || txt.contains("낮")){
            set_Time("12:00");
            return;
        }
        if(txt.contains("저녁")){
            set_Time("19:00");
            return;
        }

        set_Time("N");
    }


}
