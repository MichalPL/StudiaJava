/**
 * Created by Michal on 2015-11-28.
 */
public class Event {
    private int ID;
    private String name;
    private PriorityEnum priority; //wysoki, normalny, niski
    private String text;
    private ProgressEnum progress; //do zrobienia, zrobione, robione...
    private boolean status; //do usuniecia, nie do usuniecia

    @Override
    public String toString() {
<<<<<<< HEAD
        return  "ID: " + ID + "\nNazwa: " + name + "\nPriorytet: "
=======
        return "ID: " + ID + "\nNazwa: " + name + "\nPriorytet: "
>>>>>>> origin/master
                + priority + "\nOpis: " + text + "\nPostęp: "
                + progress + "\n=========================\n";
    }

    public Event(int id, String name, PriorityEnum priority, String text, ProgressEnum progress) {
        ID = id;
        this.name = name;
        this.priority = priority;
        this.text = text;
        this.progress = progress;
        this.status = false;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority){
        this.priority = priority;
    }

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProgressEnum getProgress(){
        return progress;
    }

    public void setProgress(ProgressEnum progress){
        this.progress = progress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
