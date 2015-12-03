<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;
=======
import java.util.*;
>>>>>>> origin/master

/**
 * Created by Michal on 2015-11-28.
 */
public class ListOfCategories extends ArrayList {
    public ArrayList<Category> alist;

    public ListOfCategories() {
        alist = new ArrayList<Category>();
    }

    public void addCategory(String...names) {
        for (String name : names) {
            Category newCat = new Category(name);
            alist.add(newCat);
        }
    }

    public boolean search(String name) {
<<<<<<< HEAD
        return alist.stream().anyMatch(s->Objects.equals(s.toString(),name));
=======
        boolean r = false;
        for(int i = 0; i < alist.size(); i++)
        {
            if(Objects.equals(alist.get(i).toString(), name)) {
                r = true;
            }
        }
        return r;
>>>>>>> origin/master
    }

    @Override
    public String toString(){
        String categs = "Obecne kategorie: \n";
        for (Category anAlist : alist)
            categs += anAlist.toString() + "\n";
        return categs;
    }
}
