<<<<<<< HEAD
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
=======
import com.sun.org.apache.bcel.internal.util.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
>>>>>>> origin/master

/**
 * Created by Michal on 2015-11-28.
 */
public class ListOfEvents extends HashMap {
<<<<<<< HEAD
    public Map<Category, Event> alist;
    public List<Event> events;
=======
    public HashMap<Category, Event> alist;
    public ArrayList<Event> events;
>>>>>>> origin/master
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

<<<<<<< HEAD
    public List<Event> byPriority(String cat, PriorityEnum prior) {
         return alist.entrySet().stream().filter(s->s.getKey().toString().equals(cat) && s.getValue().getPriority().equals(prior))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Map<Category, Event> getEventsAndCat() {
        return alist;
    }

    public List<Event> getEvents() {
=======
    public String byPriority(String cat, PriorityEnum prior) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Category, Event> entry: alist.entrySet())
        {
            if (entry.getKey().toString().equals(cat)) {
                if (entry.getValue().getPriority().equals(prior)) {
                    sb.append(entry.getValue());
                    /*.getID()).append(" ")
                            .append(entry.getValue().getName()).append(" ")
                            .append(entry.getValue().getPriority()).append(" ")
                            .append(entry.getValue().getProgress())
                            .append("\n"*/
                }
            }
        }
        return sb.toString();
    }

    public HashMap<Category, Event> getEventsAndCat() {
        return alist;
    }

    public ArrayList<Event> getEvents() {
>>>>>>> origin/master
        return events;
    }

    public ListOfCategories getCat() {
        return loc;
    }
}
