import java.util.*;

/**
 * Created by Michal on 2016-03-17.
 */
public class MnemonicMap {
    private Map<String, String> map;
    private Map<String, Integer> argCountMap;

    public MnemonicMap() {
        map = new HashMap<String, String>();
        argCountMap = new HashMap<String, Integer>();
        fill();
    }

    private void fill()
    {
        map.put("add",     "00000"); //dodawanie rejestr, liczba
        argCountMap.put("add", 2);

        map.put("addx",    "00001"); //dodawanie rejestr, rejestr
        argCountMap.put("addx", 2);

        map.put("sub",     "00010"); //odejmowanie rejestr, liczba
        argCountMap.put("sub", 2);

        map.put("subx",    "00011"); //odejmowanie rejestr, rejestr
        argCountMap.put("subx", 2);

        map.put("test",    "00100"); //odejmowanie rejestr, rejestr
        argCountMap.put("test", 1);
    }

    public int getArgCount(String name)
    {
        if(!argCountMap.containsKey(name)) return -1;
        return argCountMap.get(name);
    }

    public boolean isMnemonic(String name) {
        return !name.isEmpty() && map.containsKey(name);
    }

    public String getBinary(String name)
    {
        if(name.isEmpty()) return "";
        return map.get(name);
    }
}
