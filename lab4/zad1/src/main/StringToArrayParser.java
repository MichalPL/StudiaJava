package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Michal on 2015-12-30.
 */
public class StringToArrayParser {
    public String toStr(ArrayList<String> list) {
        String s = "";
        for (String aList : list) {
            s += aList + ";";
        }
        return s;
    }

    public ArrayList<String> toArr(String s) {
        String[] aString = s.split(";");
        return new ArrayList<String>(Arrays.asList(aString));
    }

}
