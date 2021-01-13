package pl.spliter;

import java.util.ArrayList;
import java.util.List;

public class StringSpliter {
    public static List<String> split(String s){
        List<String> stringList = new ArrayList<>();
        String [] arrOfStr = s.split(",");

        for (String a : arrOfStr){
            if(!a.contains("rates")){
                stringList.add(a);
            }
        }
        stringList.sort(null);
        return stringList;
    }
}
