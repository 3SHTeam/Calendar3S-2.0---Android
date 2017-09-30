package com.bignerdranch.android.calendar3s.Message;

import android.util.Log;

import com.bignerdranch.android.calendar3s.Json.SelectScheduleKeywordJson;
import com.bignerdranch.android.calendar3s.SQL.SelectSqlMaster;
import com.bignerdranch.android.calendar3s.data.KeywordData;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Owner on 2017-09-15.
 */

public class ScheduleTextChecker {

    private ArrayList<ArrayList<Pattern>> patterns = new ArrayList<ArrayList<Pattern>>();
    private ArrayList<KeywordData> nnkeywords = new ArrayList<KeywordData>();
    private ArrayList<KeywordData> vvkeywords = new ArrayList<KeywordData>();

    private String[] patternText = {"(\\s*[1-9]|[0-2][0-9]|3[0-1])일@(\\S*)에서",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일까지",
            "(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일부터@(\\s*[1-9]|0[1-9]|1[1-2])월(\\s[1-9]|[0-2][0-9]|3[0-1])일까지@(\\S+)에서",
            "(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일부터@(\\s[1-9]|[0-2][0-9]|3[0-1])일까지",
            "(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일\\(([월화수목금토일](|요일))\\)[\\s\\W]{1,2} "
                    + "(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일\\(([월화수목금토일](|요일))\\)",
            "([월화수목금토일](|요일))까지@(평일|주말|공휴일)@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시@(\\S+)에서",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시@(\\S+)에서",
            "일시[\\s\\W]{1,2} (2[01][0-9][0-9])년 (\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일\\"
                    + "(([월화수목금토일](|요일))@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시 (\\s*[1-9]|[0-5][0-9])분"
                    + "[\\s\\W]{1,2} (|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시 (\\s*[1-9]|[0-5][0-9])분@장소[\\s\\W]{1,2} (.+)\\n",
            "(\\S*)에서@(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일까지",
            "(\\s*[1-9]|0[1-9]|1[1-2])월 (\\s[1-9]|[0-2][0-9]|3[0-1])일 ([월화수목금토일])(|요일)@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일@(|오전|오후|[AaPp][Mm]) (\\s*[1-9]|[0-1][0-9]|2[0-3])시 (\\s*[1-9]|[0-5][0-9])분",
            "(\\s*[1-9]|[0-2][0-9]|3[0-1])일부터",
            "(0[1-9]|1[0-2]|[1-9])월\\s{0,1}(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])일",
            "(0[1-9]|1[0-2]|[1-9])\\/(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\/\\d]",
            "(0[1-9]|1[0-2]|[1-9])-(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\-\\d]",
            "(0[1-9]|1[0-2]|[1-9])\\.(0[0-9]|[1-9]|[1-2][0-9]|3[0-1])[^\\.\\d]",
            "(0[1-9]|[1-2][0-9]|3[0-1]|[1-9])일\\s{0,1}[뒤후]",
            "(0[1-9]|[1-2][0-9]|3[0-1]|[1-9])일",
            "(0[1-9]|1[0-2]|[1-9])(월|월달)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])",
            "([1-9])(월|월달)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])",
            "(|이번달|다음달|다다음달)( |)(첫|둘|셋|넷|다섯)(째주|째)( |)([월화수목금토일])",
            "(|이번주|다음주|다다음주|담주|다담주)( |)(월|화|수|목|금|토|[^내]일)요일",
            "(0[1-9]|1[0-2]|[1-9])개월( |)[뒤후]",
            "([1-9])개월( |)[뒤후]",
            "([1-4])주( |)[뒤후]", "(\\S*)해{0,1}봐([\\s\\.]|$)",
            "(\\S*)해{0,1}보지([\\s\\.\\?]|$)", "(\\S*)해{0,1}보는[거건]\\s어때([\\s\\.\\?]|$)",
            "(\\S*)해{0,1}봅시다([\\s\\.]|$)", "(\\S*)해{0,1}보자([\\s\\.]|$)",
            "(\\S*)해{0,1}볼래([\\s\\.\\?]|$)", "(\\S*)해{0,1}주길\\s바라([\\s\\.]|$)",
            "(\\S*)해{0,1}주실래요([\\s\\.\\?]|$)", "(\\S*)해{0,1}서\\s(\\S*)하자([\\s\\.]|$)",
            "(\\S*)하{0,1}자([\\s\\.]|$)", "(\\S*)하{0,1}자[는고]([\\s\\.]|$)",
            "(\\S*)하{0,1}자니까([\\s\\.\\?]|$)", "(\\S*)하{0,1}지([\\s\\.\\?]|$)",
            "(\\S*)하{0,1}세요([\\s\\.]|$)", "(\\S*)하{0,1}십시오([\\s\\.]|$)",
            "(\\S*)하{0,1}실\\s수\\s있나요([\\s\\.\\?]|$)", "(\\S*)하{0,1}실레요([\\s\\.\\?]|$)",
            "(\\S*)하{0,1}고\\s싶다([\\s\\.]|$)", "(\\S*)하{0,1}고\\s(\\S*)자([\\s\\.]|$)",
            "(\\S*)하{0,1}는\\s겁니다([\\s\\.]|$)", "(\\S*)하{0,1}는[거건]\\s어때([\\s\\.\\?]|$)",
            "(\\S*)하{0,1}는게\\s좋겠어([\\s\\.]|$)", "(\\S*)하{0,1}는\\s거야([\\s\\.]|$)",
            "(\\S*)하{0,1}는\\s것을\\s(\\S*)한다([\\s\\.]|$)", "(\\S*)하{0,1}기를\\s(\\S*)한다([\\s\\.]|$)",
            "(\\S*)하{0,1}기로\\s합시다([\\s\\.]|$)", "(\\S*)하{0,1}도록\\s합시다([\\s\\.]|$)",
            "(\\S*)(만나|모이|보)든지", "(\\S*)(만나|보|모이)게([\\s\\.]|$)",
            "(\\S*)할{0,1}까요([\\s\\.\\?]|$)", "(\\S*)할{0,1}래([\\s\\.\\?]|$)",
            " (\\S*)할{0,1}\\s수\\s있을까([\\s\\.\\?]|$)", "(\\S*)할{0,1}\\s수\\s있는\\s거야([\\s\\.\\?]|$)",
            "(\\S*)할{0,1}\\s수\\s있기를\\s(\\S*)한다([\\s\\.]|$)", "(\\S*)합{0,1}시다([\\s\\.]|$)",
            "(^|\\s)[월화수목금토일](요일|\\s|$)",
            "(이번주|다음주|담주|다담주|다다음주)",
            "(이번달|다음달)",
            "(모레|이튿|이틀|다다음날|내일|낼|다음날|담날|글피|사흗|사흘|나흗|나흘|닷샛|닷새|엿샛|엿새)",
            "([^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]|\\s|^)장\\s*소([^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]|)",
            "([1-9]|0[1-9]|1[1-2])월@(\\s[1-9]|[0-2][0-9]|3[0-1])일\\s{0,1}[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{1,3}\\s{0,1}(\\s[1-9]|[0-2][0-9]|3[0-1])일",
            "(\\s[1-9]|[0-2][0-9]|3[0-1])일부터@\\d{1,2}박\\s{0,1}\\d{1,2}일@(\\S+)에서"
    };

    public ScheduleTextChecker() {
        setKeywords();
        setPatterns(patternText);
    }

    public boolean scheduleTextCheck(String text) {
        Log.d("ssh_check", text);

        if (text.equals("")) {// 수집한 텍스트가 없을경우
            Log.e("scheduleTextCheck", "수집한 텍스트가 비어있습니다.");
        } else {// 수집한 텍스트가 있는 경우
            String[] arrText = text.split("\\.(\\D|$)"); // 텍스트를 문장단위로 쪼갠

            int count = 0;
            Matcher matcher = null;

            for (int j = 0; j < arrText.length; j++) {
                for (int NKEY = 0; NKEY < nnkeywords.size(); NKEY++) {
                    if (arrText[j].contains(nnkeywords.get(NKEY).getData(1))) {
                        // 패턴 찾기
                        for (int pl = 0; pl < patterns.size(); pl++) {
                            count = 0;
                            Log.d("check_pattern name", nnkeywords.get(NKEY).getData(1));
                            Log.d("check_pattern size", String.valueOf(patterns.get(pl).size()));
                            for (int p = 0; p < patterns.get(pl).size(); p++) {
                                matcher = patterns.get(pl).get(p).matcher(arrText[j]);
                                if (matcher.find()) count++;

                                Log.d("check_pattern count", String.valueOf(count));
                                if (count == (patterns.get(pl).size())) {
                                    Log.d("scheduleTextCheck", nnkeywords.get(NKEY).getData(1) + " 발견!!!");
                                    return true;
                                }
                            }
                        }
                    }
                }
                for (int VKEY = 0; VKEY < vvkeywords.size(); VKEY++) {
                    if (arrText[j].contains(vvkeywords.get(VKEY).getData(1))) {
                        // 패턴 찾기
                        for (int pl = 0; pl < patterns.size(); pl++) {
                            count = 0;
                            for (int p = 0; p < patterns.get(pl).size(); p++) {
                                matcher = patterns.get(pl).get(p).matcher(arrText[j]);
                                if (matcher.find())
                                    count++;
                                if (count == (patterns.get(pl).size())) {
                                    Log.d("scheduleTextCheck", vvkeywords.get(VKEY).getData(1) + " 발견!!!");
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private void setPatterns(String[] patternText) {
        for (int i = 0; i < patternText.length; i++) {
            String[] set = patternText[i].split("@");
            ArrayList<Pattern> patternAry = new ArrayList<Pattern>();
            if (set.length > 0) {
                for (int j = 0; j < set.length; j++)
                    patternAry.add(Pattern.compile(set[j]));
            }
            patterns.add(patternAry);
        }
    }

    private void setKeywords() {
        SelectSqlMaster sqlMaster = new SelectSqlMaster();
        sqlMaster.selectKeywords();
        SelectScheduleKeywordJson json = new SelectScheduleKeywordJson();
        json.onPostExecute(sqlMaster.runSqlMessage());

        this.vvkeywords = json.getvvkeywords();
        this.nnkeywords = json.getnnkeywords();
//        for (int i = 0; i < vvkeywords.size(); i++)
//            System.out.println(vvkeywords.get(i).getData(1));
//        for (int i = 0; i < nnkeywords.size(); i++)
//            System.out.println(nnkeywords.get(i).getData(1));
    }
}
