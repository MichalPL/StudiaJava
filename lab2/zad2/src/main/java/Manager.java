import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Michal on 2015-12-03.
 */
public class Manager {
    private ListOfEvents loe;

    public Manager(ListOfEvents loe) {
        this.loe = loe;
    }
    private Event findEventById(int id) {
        return loe.getEventsAndCat().entrySet().stream()
                .filter(s->Objects.equals(s.getValue().getID(), id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()).get(0);
    }

    private void addCat(String name) {
        loe.getCat().addCategory(name);
    }

    public void addEvent(String category, int id, String name, String text, String priority, String progress) {
        Event newEv = new Event(id,name,PriorityEnum.valueOf(priority),text,ProgressEnum.valueOf(progress));
        loe.getEvents().add(newEv);
        Category cat = new Category(category);
        if(!loe.getCat().search(category)) {
            loe.getCat().addCategory(category);
        }
        loe.getEventsAndCat().put(cat, newEv);
    }

    public void eventDone(int id) throws Exception {
        Event event = findEventById(id);
        event.setProgress(ProgressEnum.ZROBIONE);
    }

    public void removeEventbyId(int id) throws Exception {
        Event event = findEventById(id);
        event.setStatus(true);
        loe.removeEvent(event);
    }

    public List<Event> returnEventsNotDone() {
        return loe.getEventsAndCat().entrySet().stream()
                .filter(s->Objects.equals(s.getValue().getProgress(),ProgressEnum.DO_ZROBIENIA))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Event> returnEventsByPriority(String cat) {
        List<Event> newList = new ArrayList<>(loe.byPriority(cat, PriorityEnum.HIGH));
        newList.addAll(loe.byPriority(cat, PriorityEnum.NORMAL));
        newList.addAll(loe.byPriority(cat, PriorityEnum.LOW));
        return newList;
    }

    public ListOfEvents getListOfEvents() {
        return loe;
    }
}
