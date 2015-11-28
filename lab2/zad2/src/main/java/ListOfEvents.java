import com.sun.org.apache.bcel.internal.util.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 2015-11-28.
 */
public class ListOfEvents extends HashMap {
    public HashMap<Category, Event> alist;
    public ArrayList<Event> events;
    public ListOfCategories loc;

    public ListOfEvents(String...defcateg) {
        loc = new ListOfCategories();
        events = new ArrayList<Event>();
        for(String category : defcateg) {
            loc.addCategory(category);
        }
        alist = new HashMap<Category, Event>();
    }

    public void removeEvent(Event event) {
        if(event.getStatus().equals(true)){
            alist.values().remove(event);
        }
    }

    public String byPriority(String cat, PriorityEnum prior) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Category, Event> entry: alist.entrySet())
        {
            if (entry.getKey().toString().equals(cat)) {
                if (entry.getValue().getPriority().equals(prior)) {
                    sb.append(entry.getValue().getID()).append(" ")
                            .append(entry.getValue().getName()).append(" ")
                            .append(entry.getValue().getPriority()).append(" ")
                            .append(entry.getValue().getProgress())
                            .append("\n");
                }
            }
        }
        return sb.toString();
    }

    public HashMap<Category, Event> getEventsAndCat() {
        return alist;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ListOfCategories getCat() {
        return loc;
    }
}
