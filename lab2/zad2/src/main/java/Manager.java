import com.oracle.jrockit.jfr.NoSuchEventException;
import com.sun.org.apache.bcel.internal.util.Objects;
import java.util.Map;

/**
 * Created by Michal on 2015-11-28.
 */
public class Manager {
    public ListOfEvents loe;

    public Manager() {
        loe = new ListOfEvents("Dzisiaj", "Jutro", "Za tydzień", "Za rok"); //defaultowe kategorie
    }

    public void run() throws NoSuchEventException {
        addEvent("Dzisiaj", 11212, "asasas", "sdfdfgds", "HIGH", "DO_ZROBIENIA");
        addEvent("Dzisiaj", 18722, "asasas", "sdadsdsds", "LOW", "DO_ZROBIENIA");
        addEvent("Dzisiaj", 452, "asasas", "sdfdsds", "NORMAL", "DO_ZROBIENIA");
        addEvent("a34hghaa", 164512, "asdasas", "12sdfds", "HIGH", "ZROBIONE");
        System.out.println(showEventsNotDone());
        eventDone(11212);
        System.out.println(showEventsByPriority("asasas"));
        removeEventbyId(452);
        System.out.println(showEventsNotDone());
    }

    private Event findEventById(int id) {
        Event event = null;
        for(Map.Entry<Category, Event> entry: loe.getEventsAndCat().entrySet())
        {
            if (Objects.equals(entry.getValue().getID(),id)) {
                event = entry.getValue();
            }
        }
        return event;
    }

    private void addCat(String name) {
        loe.getCat().addCategory(name);
    }

    private void addEvent(String category, int id, String name, String text, String priority, String progress) {
        Event newEv = new Event(id,name,PriorityEnum.valueOf(priority),text,ProgressEnum.valueOf(progress));
        loe.getEvents().add(newEv);
        Category cat = new Category(category);
        if(!loe.getCat().search(category)) {
            System.out.println(String.format("Nie znaleziono kategorii \"%s\". Została dodana automatycznie.", cat.toString()));
            loe.getCat().addCategory(category);
        }
        loe.getEventsAndCat().put(cat, newEv);
    }

    private void eventDone(int id) throws NoSuchEventException {
        Event event = findEventById(id);
        event.setProgress(ProgressEnum.ZROBIONE);
    }

    private void removeEventbyId(int id) {
        Event event = findEventById(id);
        event.setStatus(true);
        loe.removeEvent(event);
    }

    private String showEventsNotDone() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Category, Event> entry: loe.getEventsAndCat().entrySet())
        {
            if (Objects.equals(entry.getValue().getProgress(),ProgressEnum.DO_ZROBIENIA)) {
                sb.append(entry.getValue().getID()).append(" ")
                        .append(entry.getValue().getName()).append(" ")
                        .append(entry.getValue().getPriority()).append(" ")
                        .append(entry.getValue().getProgress())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    private String showEventsByPriority(String cat) {
        return loe.byPriority(cat, PriorityEnum.HIGH) +
                loe.byPriority(cat, PriorityEnum.NORMAL) +
                loe.byPriority(cat, PriorityEnum.LOW);
    }
}
