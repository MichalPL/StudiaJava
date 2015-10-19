package zad1.Objects;

import java.util.*;

/**
 * Created by Michal on 2015-10-09.
 */
public class ListOfSource implements AbstractObject{
    private Map<String, SourceHtmlList> list;

    public ListOfSource() {
        list = new LinkedHashMap<>();
    }

    @Override
    public int getSize() {
        return list.size();
    }

    public void addNewLine(String url, SourceHtmlList html) {
        list.put(url, html);
    }

    public String getByIndex(int index){
        int counter = 0;
        String url;
        SourceHtmlList value;
        String ret = "Out of index!";
        if (index <= getSize()) {
            for (Map.Entry<String, SourceHtmlList> entry : list.entrySet()) {
                if (Objects.equals(counter, index)) {
                    url = entry.getKey();
                    value = entry.getValue();
                    ret = url + ": " + System.getProperty("line.separator") + value.getAllItems();
                }
                counter++;
            }
        }
        return ret;
    }

    public void showAllPositions() {
        int counter = 0;
        String url;
        for (Map.Entry<String, SourceHtmlList> entry : list.entrySet()) {
                url = entry.getKey();
                System.out.println(counter + ": " + url);
            counter++;
        }
    }

    public void showItem(int index) {
        System.out.println(getByIndex(index));
    }
}
