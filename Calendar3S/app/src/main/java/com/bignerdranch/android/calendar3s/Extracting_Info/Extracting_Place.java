package com.bignerdranch.android.calendar3s.Extracting_Info;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extracting_Place {
    private String place;

    public Extracting_Place() {
        place = "I";
    }

    public String get_Place() {
        return place;
    }

    public void set_Place(String group) {
        place = group;
    }

    public void Place_Recognition(String message) {
        String or_text;

        Pattern PlaceP1 = Pattern.compile(
                "([^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]|\\s|^)장\\s*소([^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]|)( |)([^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]*)( |)(.+)");

        Pattern PlaceP8 = Pattern.compile("\\d일까지.+(\\d{1,3}일|하루|이틀|사흘|나흘|닷새|엿새|이레|양일)\\s{0,1}(간의{0,1}|동안)\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP8_0 = Pattern.compile("\\d일(부터){0,1} (\\d{1,3}일|하루|이틀|사흘|나흘|닷새|엿새|이레|양일)\\s{0,1}(간의{0,1}|동안)\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP8_1 = Pattern.compile("\\d월(부터){0,1} (\\d{1,3}일|하루|이틀|사흘|나흘|닷새|엿새|이레|양일)\\s{0,1}(간의{0,1}|동안)\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");

        Pattern PlaceP10 = Pattern.compile("\\d{1,2}일{0,1}\\s{0,1}[,~-]\\s{0,1}\\d{1,2}일\\s(.+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP10_0 = Pattern.compile("(\\d{1,2}일|하루|이틀|사흘|나흘|닷새|엿새|이레|양일)에\\s걸쳐\\s(.+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)");

        Pattern PlaceP7 = Pattern.compile("\\(.+\\)까지\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_0 = Pattern.compile("\\d분까지\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_1 = Pattern.compile("\\d시까지\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_2 = Pattern.compile("\\d일까지\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_3 = Pattern.compile("\\d월까지\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_4 = Pattern.compile("\\d분부터는{0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_5 = Pattern.compile("\\d시부터는{0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_6 = Pattern.compile("\\d일부터는{0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP7_7 = Pattern.compile("\\d월부터는{0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");

        Pattern PlaceP2 = Pattern.compile("\\d분(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP2_1 = Pattern.compile("\\d시(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP2_2 = Pattern.compile("\\d일(에는{0,1}){0,1}[,\\)]{0,1}([\\<\\[\\{\\(].+[\\>\\]\\}\\)]){0,1}(\\s오전|\\s오후){0,1}"
                + "(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP2_3 = Pattern.compile("\\d월(에는{0,1}){0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");

        Pattern PlaceP11_0 = Pattern.compile("\\d분(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}\\s{0,1}(.+)( |)[보가갈][서자요래]\\s{0,1}");
        Pattern PlaceP11_1 = Pattern.compile("\\d시(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}(.+)( |)[보가갈][서자요래]\\s{0,1}");
        Pattern PlaceP11_2 = Pattern.compile("\\d일(에는{0,1}){0,1}[,\\)]{0,1}([\\<\\[\\{\\(].+[\\>\\]\\}\\)]){0,1}(\\s오전|\\s오후){0,1}"
                + "(.+)( |)[가갈][서자요래]\\s{0,1}");
        Pattern PlaceP11_a = Pattern.compile("(모레|이튿|이틀|다다음\\\\s{0,1}날|내일|낼|다음\\\\s{0,1}날|담날|글피|사흗|사흘|나흗|나흘|닷샛|닷새|엿샛|엿새)"
                + "\\s{0,1}[즘쯤]{0,1}(.+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP11_b = Pattern.compile("(모레|이튿|이틀|다다음\\\\s{0,1}날|내일|낼|다음\\\\s{0,1}날|담날|글피|사흗|사흘|나흗|나흘|닷샛|닷새|엿샛|엿새)"
                + "\\s{0,1}[즘쯤]{0,1}(.+)[가보갈][서자요래]\\s{0,1}");
        Pattern PlaceP11_c = Pattern.compile("[월화수목금토일]요일(에는{0,1}){0,1}\\s{0,1}(.+)( |)[보가갈][서자요래]\\s{0,1}");
        Pattern PlaceP11_d = Pattern.compile("[월화수목금토일]요일(에는{0,1}){0,1}\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");

        Pattern PlaceP3 = Pattern.compile("\\d분(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}\\s{0,1}(.+)서\\s");
        Pattern PlaceP3_1 = Pattern.compile("\\d시(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}(.+)( |)서\\s");
        Pattern PlaceP3_2 = Pattern.compile("\\d일(에는{0,1}){0,1}[,\\)]{0,1}([\\<\\[\\{\\(].+[\\>\\]\\}\\)]){0,1}(\\s오전|\\s오후){0,1}"
                + "(.+)( |)서\\s");
        Pattern PlaceP3_3 = Pattern.compile("\\d월(에는{0,1}){0,1}(.+)( |)서\\s");

        Pattern PlaceP9 = Pattern.compile("(.+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP4 = Pattern.compile("(\\S+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP4_1 = Pattern.compile("(\\S+)서\\s");
        Pattern PlaceP5 = Pattern.compile("[은는이가을를로에고의]\\s(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP5_1 = Pattern.compile("[은는이가을를로에고의]\\s(.+)서\\s");
        Pattern PlaceP6 = Pattern.compile("[^A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s]\\s{0,1}(.+)( |)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Pattern PlaceP6_1 = Pattern.compile("[^A-Za-z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\s]\\s{0,1}(.+)서\\s");



        Matcher matcher_Ass;

        Matcher matcher = PlaceP1.matcher(message);
        if (matcher.find()) {
            if (!matcher.group(1).trim().equals("")) {   //[장 소] ekngioen efkne eknfke(ekekef) [주 최] ~~~
                Pattern Place_Ass = Pattern.compile("^[a-zA-Z가-힣\\s\\(\\)]+");
                matcher_Ass = Place_Ass.matcher(matcher.group(6));
                if (matcher_Ass.find()) {
                    set_Place(matcher_Ass.group(0).trim());
                    return;
                }
            }
            set_Place(matcher.group(6).trim());
            return;
        }

        matcher = PlaceP8.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }

            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP8_0.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(4).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(4).trim());
            return;
        }
        matcher = PlaceP8_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(4).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(4).trim());
            return;
        }

        matcher = PlaceP10.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP10_0.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }


        matcher = PlaceP7.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_0.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_2.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_3.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_4.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_5.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_6.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP7_7.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }


        matcher = PlaceP2.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP2_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP2_2.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(4).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(4).trim());
            return;
        }
        matcher = PlaceP2_3.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }

        matcher = PlaceP11_0.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP11_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP11_2.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(4).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(4).trim());
            return;
        }


        matcher = PlaceP11_a.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }
        matcher = PlaceP11_b.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }
        matcher = PlaceP11_c.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }
        matcher = PlaceP11_d.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }

        matcher = PlaceP3.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP3_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(3).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(3).trim());
            return;
        }
        matcher = PlaceP3_2.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(4).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(4).trim());
            return;
        }
        matcher = PlaceP3_3.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(2).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(2).trim());
            return;
        }

        matcher = PlaceP9.matcher(message);

        if (matcher.find()) {

//         if(!in_Place(save_text).equals("N")){
//            save_text = in_Place(save_text);
//         }
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }

            if(in_Place(save_text).equals("N")){
                set_Place(save_text);
                return;
            }
        }

        matcher = PlaceP4.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP4_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }

        matcher = PlaceP5.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP5_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }

        matcher = PlaceP6.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }
        matcher = PlaceP6_1.matcher(message);
        if (matcher.find()) {
            String save_text = or_text = matcher.group(1).trim();
            if(!in_Part_Of_Speech(save_text).equals("N")){
                save_text = in_Part_Of_Speech(save_text);
            }
            if(!in_Date(save_text).equals("N")){
                save_text = in_Date(save_text);
            }

            if(!or_text.equals(save_text)){
                set_Place(save_text);
                return;
            }
            set_Place(matcher.group(1).trim());
            return;
        }

        set_Place("N");
    }

    public String in_Place(String msg){
        Pattern Place = Pattern.compile("(.+)(일대|근처|인근|주변|가까이|에[서선]는{0,1}|일원)\\s{0,1}");
        Matcher matcher = Place.matcher(msg);

        if(matcher.find()){
            return matcher.group(1).trim();
        }
        else
            return "N";
    }

    public String in_Part_Of_Speech(String msg){
        String message = msg;
        Pattern Subject0 = Pattern.compile(".+이\\s(.+)");
        Pattern Subject1 = Pattern.compile(".+가\\s(.+)");
        Pattern Subject2 = Pattern.compile(".+은\\s(.+)");
        Pattern Subject3 = Pattern.compile(".+는\\s(.+)");

        Pattern Object0 = Pattern.compile(".+를\\s(.+)");
        Pattern Object1 = Pattern.compile(".+을\\s(.+)");

        Pattern Adjective0 = Pattern.compile("\\S+(하는|한|해)\\s(.+)");
        Pattern Adjective1 = Pattern.compile("\\S+인\\s(.+)");
        Pattern Adjective2 = Pattern.compile("\\S+된\\s(.+)");

        Matcher matcher = Subject0.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }
        matcher = Subject1.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }
        matcher = Subject2.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }
        matcher = Subject3.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }

        matcher = Object0.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }
        matcher = Object1.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }

        matcher = Adjective0.matcher(message);
        if(matcher.find()){
            message = matcher.group(2);
        }
        matcher = Adjective1.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }
        matcher = Adjective2.matcher(message);
        if(matcher.find()){
            message = matcher.group(1);
        }

        if(message.equals(msg)){
            return "N";
        }
        else
            return message;

    }

    public String in_Date(String msg){
        Pattern PlaceP7_0 = Pattern.compile("\\d분까지\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_1 = Pattern.compile("\\d시까지\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_2 = Pattern.compile("\\d일까지\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_3 = Pattern.compile("\\d월까지\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_4 = Pattern.compile("\\d분부터는{0,1}\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_5 = Pattern.compile("\\d시부터는{0,1}\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_6 = Pattern.compile("\\d일부터는{0,1}\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP7_7 = Pattern.compile("\\d월부터는{0,1}\\s{0,1}(.+)\\s{0,1}");

        Pattern PlaceP2 = Pattern.compile("\\d분(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}\\s{0,1}(.+)\\s{0,1}");
        Pattern PlaceP2_1 = Pattern.compile("\\d시(에는{0,1}){0,1}(\\s오전|\\s오후){0,1}(.+)\\s{0,1}");
        Pattern PlaceP2_2 = Pattern.compile("\\d일(에는{0,1}){0,1}[\\,\\)]{0,1}([\\<\\[\\{\\(].+[\\>\\]\\}\\)]){0,1}(\\s오전|\\s오후){0,1}(.+)\\s{0,1}");
        Pattern PlaceP2_3 = Pattern.compile("\\d월(에는{0,1}){0,1}(.+)\\s{0,1}");

        Matcher matcher = PlaceP7_0.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_1.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_2.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_3.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_4.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_5.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_6.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        matcher = PlaceP7_7.matcher(msg);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        matcher = PlaceP2.matcher(msg);
        if (matcher.find()) {
            return matcher.group(3).trim();
        }
        matcher = PlaceP2_1.matcher(msg);
        if (matcher.find()) {
            return matcher.group(3).trim();
        }
        matcher = PlaceP2_2.matcher(msg);
        if (matcher.find()) {
            return matcher.group(4).trim();
        }
        matcher = PlaceP2_3.matcher(msg);
        if (matcher.find()) {
            return matcher.group(2).trim();
        }

        return "N";
    }

}

