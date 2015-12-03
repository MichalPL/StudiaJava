<<<<<<< HEAD
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
=======
import com.oracle.jrockit.jfr.NoSuchEventException;
import com.sun.org.apache.bcel.internal.util.Objects;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Michal on 2015-11-28.
 */
public class Manager {
    private ListOfEvents loe;
    private Configuration conf;

    public Manager() {
        loe = new ListOfEvents("Dzisiaj", "Jutro", "Za tydzień", "Za rok"); //defaultowe kategorie
        conf = new Configuration();
    }

    public void run() throws NotFoundException, IOException {

        addEvent("Dzisiaj", 11212, "asasas", "sdfdfgds", "HIGH", "DO_ZROBIENIA");
        addEvent("Dzisiaj", 18722, "asasas", "sdadsdsds", "LOW", "DO_ZROBIENIA");
        addEvent("Dzisiaj", 452, "asasas", "sdfdsds", "NORMAL", "DO_ZROBIENIA");
        addEvent("a34hghaa", 164512, "asdasas", "12sdfds", "HIGH", "ZROBIONE");

        if(conf.showCategories())
            System.out.println(loe.getCat());

        eventDone(11212);

        if(conf.showNotDone())
            System.out.println(showEventsNotDone());

        System.out.println(showEventsByPriority(conf.getCategory()));

        removeEventbyId(452);
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
>>>>>>> origin/master
    }

    private void addCat(String name) {
        loe.getCat().addCategory(name);
    }

<<<<<<< HEAD
    public void addEvent(String category, int id, String name, String text, String priority, String progress) {
=======
    private void addEvent(String category, int id, String name, String text, String priority, String progress) throws IOException {
>>>>>>> origin/master
        Event newEv = new Event(id,name,PriorityEnum.valueOf(priority),text,ProgressEnum.valueOf(progress));
        loe.getEvents().add(newEv);
        Category cat = new Category(category);
        if(!loe.getCat().search(category)) {
<<<<<<< HEAD
=======
            System.out.println(String.format("Nie znaleziono kategorii \"%s\". Została dodana automatycznie.", cat.toString()));
>>>>>>> origin/master
            loe.getCat().addCategory(category);
        }
        loe.getEventsAndCat().put(cat, newEv);
    }

<<<<<<< HEAD
    public void eventDone(int id) throws Exception {
=======
    private void eventDone(int id) throws NotFoundException {
>>>>>>> origin/master
        Event event = findEventById(id);
        event.setProgress(ProgressEnum.ZROBIONE);
    }

<<<<<<< HEAD
    public void removeEventbyId(int id) throws Exception {
=======
    private void removeEventbyId(int id) {
>>>>>>> origin/master
        Event event = findEventById(id);
        event.setStatus(true);
        loe.removeEvent(event);
    }

<<<<<<< HEAD
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
=======
    private String showEventsNotDone() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n");
        sb.append("=    ZADANIA DO ZROBIENIA    =");
        sb.append("\n==============================\n");
        for(Map.Entry<Category, Event> entry: loe.getEventsAndCat().entrySet())
        {
            if (Objects.equals(entry.getValue().getProgress(),ProgressEnum.DO_ZROBIENIA)) {
                sb.append(entry.getValue());
                /*.getID()).append(" ")
                        .append(entry.getValue().getName()).append(" ")
                        .append(entry.getValue().getPriority()).append(" ")
                        .append(entry.getValue().getProgress())
                        .append("\n"*/
            }
        }
        return sb.toString();
    }

    private String showEventsByPriority(String cat) {
        return "\n==============================\n" + "= " + cat + " ="
                + "\n==============================\n" +
                loe.byPriority(cat, PriorityEnum.HIGH) +
                loe.byPriority(cat, PriorityEnum.NORMAL) +
                loe.byPriority(cat, PriorityEnum.LOW);
>>>>>>> origin/master
    }
}
