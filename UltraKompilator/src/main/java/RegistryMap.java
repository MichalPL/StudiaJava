import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 2016-03-18.
 */
public class RegistryMap {
    Map<String, String> map;

    public RegistryMap() {
        map = new HashMap<String, String>();
        fill();
    }

    private void fill()
    {
        map.put("a",    "000");
        map.put("b",    "001");
        map.put("r0",   "010");
        map.put("r1",   "011");
        map.put("r2",   "100");
        map.put("r3",   "101");
        map.put("r4",   "110");
        map.put("r5",   "111");

    }

    public boolean isRegistry(String name) {
        return !name.isEmpty() && map.containsKey(name);
    }

    public String getBinary(String name)
    {
        if(name.isEmpty()) return "";
        return map.get(name);
    }
}
