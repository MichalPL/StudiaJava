import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Michal on 2015-11-28.
 */
public class EventManager {

    private ListOfEvents loe;
    private ListOfCategories loc;

    public EventManager(ListOfCategories defaultcat) throws IOException {
        loc = defaultcat;
        for(Object category : loc) {
            loe.put(category, new ArrayList<Event>());
        }
        //loc.foreach(category -> loe.put(category, new ArrayList<Event>())); ??? nie dziala

    }

}
