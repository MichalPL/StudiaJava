/**
 * Created by Michal on 2015-11-28.
 */
public class Category {
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public Category(String name) {
        this.name = name;
    }
}
