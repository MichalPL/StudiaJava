import java.util.List;

/**
 * Created by Michal on 2015-11-28.
 */
public class Printer {
    private Manager manager;
    private Configuration conf;

    public Printer() {
        manager = new Manager(new ListOfEvents("Dzisiaj", "Jutro", "Za tydzień", "Za rok"));
        conf = new Configuration();
    }

    public void run() throws Exception {

        manager.addEvent("Dzisiaj", 11212, "asasas", "sdfdfgds", "HIGH", "DO_ZROBIENIA");
        manager.addEvent("Dzisiaj", 18722, "asasas", "sdadsdsds", "LOW", "DO_ZROBIENIA");
        manager.addEvent("Dzisiaj", 452, "asasas", "sdfdsds", "NORMAL", "DO_ZROBIENIA");
        manager.addEvent("a34hghaa", 164512, "asdasas", "12sdfds", "HIGH", "ZROBIONE");

        if(conf.gePropertytAsBool("SHOW_CATEGORIES"))
            System.out.println(manager.getListOfEvents().getCat());

        manager.eventDone(11212);

        if(conf.gePropertytAsBool("SHOW_NOTDONE"))
            showEventsNotDone();

        showEventsByPriority(conf.getPropertyAsString("CATEGORY"));

        manager.removeEventbyId(452);
    }


    private void showEventsNotDone() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n");
        sb.append("=    ZADANIA DO ZROBIENIA    =");
        sb.append("\n==============================\n");
        System.out.println(sb.toString());
        List<Event> list = manager.returnEventsNotDone();
        list.stream().forEach(System.out::println);
    }

    private void showEventsByPriority(String cat) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n==============================\n");
        sb.append("=  ").append(cat).append("   =");
        sb.append("\n==============================\n");
        System.out.println(sb.toString());
        List<Event> list = manager.returnEventsByPriority(cat);
        list.stream().forEach(System.out::println);
    }
}
