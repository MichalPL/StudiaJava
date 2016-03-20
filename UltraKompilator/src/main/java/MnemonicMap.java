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

        map.put("mul",      "00100");
        argCountMap.put("mul", 2);

        map.put("mulx",      "00101");
        argCountMap.put("mulx", 2);

        map.put("mov",      "00110");
        argCountMap.put("mov", 2);

        map.put("movx",      "00111");
        argCountMap.put("movx", 2);

        map.put("inc",      "01000");
        argCountMap.put("inc", 1);

        map.put("dec",      "01001");
        argCountMap.put("dec", 1);

        map.put("cmp",      "01010");
        argCountMap.put("cmp", 2);

        map.put("cmpx",      "01011");
        argCountMap.put("cmpx", 2);

        map.put("or",      "01100");
        argCountMap.put("or", 2);

        map.put("orx",      "01101");
        argCountMap.put("orx", 2);

        map.put("and",      "01110");
        argCountMap.put("and", 2);

        map.put("andx",      "01111");
        argCountMap.put("andx", 2);

        map.put("neg",      "10000");
        argCountMap.put("neg", 1);

        map.put("negx",      "10001");
        argCountMap.put("negx", 1);

        map.put("jmp",      "10010");
        argCountMap.put("jmp", 1);

        map.put("nop",      "10011");
        argCountMap.put("nop", 0);

        map.put("movzx",      "10100");
        argCountMap.put("movzx", 1);

        map.put("xor",      "10101");
        argCountMap.put("xor", 2);

        map.put("xorx",      "10110");
        argCountMap.put("xorx", 2);

        map.put("clr",      "10111");
        argCountMap.put("clr", 0);

        map.put("jm",      "11000");
        argCountMap.put("jm", 1);

        map.put("jw",      "11001");
        argCountMap.put("jw", 1);

        map.put("jr",      "11010");
        argCountMap.put("jr", 1);

        map.put("swapx",      "11011");
        argCountMap.put("swapx", 2);

//        map.put("test",    "00100"); //odejmowanie rejestr, rejestr
//        argCountMap.put("test", 1);

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
