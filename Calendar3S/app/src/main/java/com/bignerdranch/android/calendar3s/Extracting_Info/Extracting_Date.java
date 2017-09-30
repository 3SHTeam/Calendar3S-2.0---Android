package com.bignerdranch.android.calendar3s.Extracting_Info;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extracting_Date {
    private String stand_Date;
    private String date;

    public Extracting_Date() {
        date = "I";
    }

    public String get_Date() {
        return date;
    }

    public void set_Date(String group) {
        date = group;
    }

    public String get_stand_Date() {
        return stand_Date;
    }

    public void set_stand_Date(String date) {
        stand_Date = date;
    }

    public void get_Present_Date() { // 현재시간 가져오기
        long now = System.currentTimeMillis();
        Date date_now = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        set_stand_Date(sdfNow.format(date_now));
    }

    public void Date_Recognition(String message, String msg_date) {
        int dayofweek = 0;
        String datearry[];
        int year, month, day;
        datearry = msg_date.split("/");
        year = Integer.valueOf(datearry[0]).intValue();
        month = Integer.valueOf(datearry[1]).intValue();
        day = Integer.valueOf(datearry[2]).intValue();
        Pattern DateP1 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])월( |)(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])일");
        Pattern DateP2 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])\\/(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\/\\d]");
        Pattern DateP3 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])-(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\-\\d]");
        Pattern DateP4 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])\\.(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\.\\d]");

        Pattern DateP5 = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1]|[1-9])일( |)[뒤후]");
        Pattern DateP6 = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1]|[1-9])일");

        Pattern DateP7 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])(월|월달)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])");
        Pattern DateP7_1 = Pattern.compile("([1-9])(월|월달)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])");
        Pattern DateP8 = Pattern.compile("(이번달|다음달|다다음달|)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])");

        Pattern DateP9 = Pattern.compile("(이번주|다음주|다다음주|담주|다담주|)( |)(월|화|수|목|금|토|일)요일");

        Pattern Date10 = Pattern.compile("(0[1-9]|1[0-2]|[1-9])개월( |)[뒤후]");
        Pattern Date10_1 = Pattern.compile("([1-9])개월( |)[뒤후]");
        Pattern Date11 = Pattern.compile("([1-4])주( |)[뒤후]");

        Matcher matcher = DateP1.matcher(message);
        if (matcher.find()) {
            set_Date(year + "/" + matcher.group(1) + "/" + matcher.group(3));
            return;
        }
        matcher = DateP2.matcher(message);
        if (matcher.find()) {
            set_Date(year + "/" + matcher.group(1) + "/" + matcher.group(2));
            return;
        }
        matcher = DateP3.matcher(message);
        if (matcher.find()) {
            set_Date(year + "/" + matcher.group(1) + "/" + matcher.group(2));
            return;
        }
        matcher = DateP4.matcher(message);
        if (matcher.find()) {
            set_Date(year + "/" + matcher.group(1) + "/" + matcher.group(2));
            return;
        }

        matcher = DateP5.matcher(message);
        if (matcher.find()) {
            set_Date(DateCalc(date, 0, Integer.valueOf(matcher.group(1).trim()).intValue()));
            return;
        }
        matcher = DateP6.matcher(message);
        if (matcher.find()) {
            set_Date(year + "/" + month + "/" + matcher.group(1));
            return;
        }

        matcher = DateP7.matcher(message);
        if (matcher.find()) {
            dayofweek = DayofWeek_int(matcher.group(7));
            if (matcher.group(4).contains("첫")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 1, dayofweek));
                return;
            } else if (matcher.group(4).contains("둘")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 2, dayofweek));
                return;
            } else if (matcher.group(4).contains("셋")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 3, dayofweek));
                return;
            } else if (matcher.group(4).contains("넷")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 4, dayofweek));
                return;
            } else if (matcher.group(4).contains("다섯")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 2, dayofweek));
                return;
            }
        }

        matcher = DateP7_1.matcher(message);
        if (matcher.find()) {
            dayofweek = DayofWeek_int(matcher.group(7));
            if (matcher.group(4).contains("첫")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 1, dayofweek));
                return;
            } else if (matcher.group(4).contains("둘")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 2, dayofweek));
                return;
            } else if (matcher.group(4).contains("셋")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 3, dayofweek));
                return;
            } else if (matcher.group(4).contains("넷")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 4, dayofweek));
                return;
            } else if (matcher.group(4).contains("다섯")) {
                set_Date(DayofWeekCalk_month(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 5, dayofweek));
                return;
            }
        }

        matcher = DateP8.matcher(message);
        if (matcher.find()) {
            dayofweek = DayofWeek_int(matcher.group(6));
            if (matcher.group(1).equals("다음달")) {
                if (matcher.group(3).contains("첫")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 1, 1, dayofweek));
                    return;
                } else if (matcher.group(3).contains("둘")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 1, 2, dayofweek));
                    return;
                } else if (matcher.group(3).contains("셋")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 1, 3, dayofweek));
                    return;
                } else if (matcher.group(3).contains("넷")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 1, 4, dayofweek));
                    return;
                } else if (matcher.group(3).contains("다섯")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 1, 5, dayofweek));
                    return;
                }
            } else if (matcher.group(1).equals("다다음달")) {
                if (matcher.group(3).contains("첫")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 2, 1, dayofweek));
                    return;
                } else if (matcher.group(3).contains("둘")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 2, 2, dayofweek));
                    return;

                } else if (matcher.group(3).contains("셋")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 2, 3, dayofweek));
                    return;
                } else if (matcher.group(3).contains("넷")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 2, 4, dayofweek));
                    return;
                } else if (matcher.group(3).contains("다섯")) {
                    set_Date(DayofWeekCalk_month(msg_date, month + 2, 5, dayofweek));
                    return;
                }
            } else if (matcher.group(1).equals("") || matcher.group(1).equals("이번달")) {
                if (matcher.group(3).contains("첫")) {
                    set_Date(DayofWeekCalk_month(msg_date, month, 1, dayofweek));
                    return;
                } else if (matcher.group(3).contains("둘")) {
                    set_Date(DayofWeekCalk_month(msg_date, month, 2, dayofweek));
                    return;
                } else if (matcher.group(3).contains("셋")) {
                    set_Date(DayofWeekCalk_month(msg_date, month, 3, dayofweek));
                    return;
                } else if (matcher.group(3).contains("넷")) {
                    set_Date(DayofWeekCalk_month(msg_date, month, 4, dayofweek));
                    return;
                } else if (matcher.group(3).contains("다섯")) {
                    set_Date(DayofWeekCalk_month(msg_date, month, 5, dayofweek));
                    return;
                }
            }
        }

        matcher = DateP9.matcher(message);
        if (matcher.find()) {
            dayofweek = DayofWeek_int(matcher.group(3));
            if (matcher.group(1).equals("다음주") || matcher.group(1).equals("담주")) {
                set_Date(DayofWeekCalk(msg_date, 1, dayofweek));
                return;
            } else if (matcher.group(1).equals("다다음주") || matcher.group(1).equals("다담주")) {
                set_Date(DayofWeekCalk(msg_date, 2, dayofweek));
                return;
            } else if (matcher.group(1).equals("") || matcher.group(1).equals("이번주")) {
                set_Date(DayofWeekCalk(msg_date, 0, dayofweek));
                return;
            }
        }

        if (message.contains("모레") || message.contains("이튿") || message.contains("이틀 뒤") || message.contains("이틀 후")
                || message.contains("다다음날")) {
            set_Date( DateCalc(msg_date, 0, 2));
            return;
        } else if (message.contains("내일") || message.contains("낼") || message.contains("다음날")
                || message.contains("담날")) {
            set_Date( DateCalc(msg_date, 0, 1));
            return;
        } else if (message.contains("글피") || message.contains("사흗") || message.contains("사흘 뒤")
                || message.contains("사흘 후")) {
            set_Date( DateCalc(msg_date, 0, 3));
            return;
        } else if (message.contains("나흗") || message.contains("나흘 뒤") || message.contains("나흘 후")) {
            set_Date( DateCalc(msg_date, 0, 4));
            return;
        } else if (message.contains("닷샛") || message.contains("닷새 뒤") || message.contains("닷새 후")) {
            set_Date( DateCalc(msg_date, 0, 5));
            return;
        } else if (message.contains("엿샛") || message.contains("엿새 뒤") || message.contains("엿새 후")) {
            set_Date( DateCalc(msg_date, 0, 6));
            return;
        }

        matcher = Date10.matcher(message);
        if (matcher.find()) {
            set_Date(DateCalc(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 0));
            return;
        }
        matcher = Date10_1.matcher(message);
        if (matcher.find()) {
            set_Date(DateCalc(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 0));
            return;
        }

        matcher = Date11.matcher(message);
        if (matcher.find()) {
            set_Date(DateCalc(msg_date, Integer.valueOf(matcher.group(1).trim()).intValue(), 0));
            return;
        }

        set_Date("N");

    }

    private String DateCalc(String date, int MM, int dd) { // MM과 dd를 더한 날짜를 반환
        String datearry[];
        int year, month, day;
        datearry = date.split("/");
        year = Integer.valueOf(datearry[0]).intValue();
        month = Integer.valueOf(datearry[1]).intValue();
        day = Integer.valueOf(datearry[2]).intValue();
        int month_last[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            month_last[1] = 29;
        }

        if (day + dd > month_last[month - 1]) {
            day = day + dd - month_last[month - 1];
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
            while (day > month_last[month - 1]) {

                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    month_last[1] = 29;
                } else
                    month_last[1] = 28;

                day = day - month_last[month - 1];
                month++;

                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
        } else {
            day += dd;
        }

        if (month + MM > 12) {
            month = month + MM - 12;
            if (month == 2 && day == 29) {
                day = 28;
            }
            year++;
        } else {
            month += MM;
        }

        return DateSet(year,month,day);
    }

    private int DayofWeek_int(String dayofweek) {
        int index = 0;
        switch (dayofweek) {
            case "일":
                index = 1;
                break;
            case "월":
                index = 2;
                break;
            case "화":
                index = 3;
                break;
            case "수":
                index = 4;
                break;
            case "목":
                index = 5;
                break;
            case "금":
                index = 6;
                break;
            case "토":
                index = 7;
                break;
        }

        return index;
    }

    private String DayofWeekCalk_month(String date, int MM, int n, int dayofweek) { // 몇월
        // 몇째주
        // 무슨요일
        String datearry[];
        int year, month, day;
        datearry = date.split("/");
        year = Integer.valueOf(datearry[0]).intValue();
        month = Integer.valueOf(datearry[1]).intValue();
        day = Integer.valueOf(datearry[2]).intValue();
        if (MM > 12) {
            MM = MM - 12;
            year++;
        }
        int month_last[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, MM - 1);
        cal.set(Calendar.DATE, 1);
        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        int new_day = 9 - nWeek + 7 * (n - 2) + (dayofweek - 1); // 몇째주 몇요일(둘째주
        // 이상)
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            month_last[1] = 29;
        }

        if (n != 1) {
            if (month_last[MM - 1] >= new_day) {
                return DateSet(year, MM, new_day);
            }
        } else if (n == 1) {
            if (dayofweek >= nWeek) {
                return DateSet(year, MM, 1 + dayofweek - nWeek);
            }
        }

        return DateSet(year, MM, 1);
    }

    private String DateSet(int Y, int M, int d) {
        String month, day;

        if(M<10){
            month = "0"+M;
        }
        else
            month = ""+M;

        if(d<10){
            day = "0"+d;
        }
        else
            day = ""+d;

        return Y + "/" + month + "/" + day;
    }

    private String DayofWeekCalk(String date, int week, int dayofweek){ //현 날짜와 몇주 이후 어떤 요일로 계산할 것인지 입력(1(일요일)~7(토요일))
        String datearry[];
        int year, month, day;

        datearry = date.split("/");
        year = Integer.valueOf(datearry[0]).intValue();
        month = Integer.valueOf(datearry[1]).intValue();
        day = Integer.valueOf(datearry[2]).intValue();

        int month_last[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            month_last[1] = 29;
        }

        int nWeek = zelle(year, month, day);   //메시지 받은 당일의 요일

        if(week==0 && nWeek > dayofweek){
            if(day < (dayofweek - nWeek)){
                if(month == 1){
                    day = month_last[11] - (dayofweek - nWeek - day);
                    month = 12;
                    year =- 1;
                    return DateSet(year, month, day);
                }
                else{
                    day = month_last[month - 2]-(dayofweek - nWeek - day);
                    month--;
                    return DateSet(year, month, day);
                }
            }

            day =+ (dayofweek - nWeek);
            return DateSet(year, month, day);
        }

        return DateCalc(date, 0, week*7 + (dayofweek - nWeek));
    }

    private int zelle(int y, int m, int d)   //첼러의 공식 입력 날짜의 요일을 구하는 공식으로 1은 일요일~ 7은 토요일이다.
    {
        int k, j, h;

        if(m <= 2)
        {
            m += 12;
            y--;
        }

        k = y % 100;
        j = y / 100;
        h = 21*j/4 + 5*k/4 + 13*(m+1)/5 + d - 1;

        if(h < 0) h += 7;

        return (h % 7) + 1;
    }

}