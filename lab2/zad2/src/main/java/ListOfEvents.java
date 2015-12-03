import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Michal on 2015-11-28.
 */
public class ListOfEvents extends HashMap {
    public Map<Category, Event> alist;
    public List<Event> events;
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

    public List<Event> byPriority(String cat, PriorityEnum prior) {
         return alist.entrySet().stream().filter(s->s.getKey().toString().equals(cat) && s.getValue().getPriority().equals(prior))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Map<Category, Event> getEventsAndCat() {
        return alist;
    }

    public List<Event> getEvents() {
        return events;
    }

    public ListOfCategories getCat() {
        return loc;
    }
}
