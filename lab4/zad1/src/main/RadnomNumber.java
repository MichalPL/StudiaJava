package main;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michal on 2016-01-06.
 */
public class RadnomNumber {
    public ArrayList<Integer> getRandomNumbers(int range)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= range; i++) list.add(i);
        Collections.shuffle(list);
        return list;
    }
}
