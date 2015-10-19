package zad1.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 2015-10-08.
 * Arraylist przechowywuj¹cy linie kodu Ÿród³owego strony.
 */
public class SourceHtmlList implements AbstractObject {
    private List<String> sourceList;

    public SourceHtmlList() {
        sourceList = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return sourceList.size();
    }

    public void addNewLine(String line) {
        sourceList.add(line);
    }

    public String getItem(int i) {
        return sourceList.get(i);
    }

    public String getAllItems() {
        String all = "";
        for(int i = 0; i < sourceList.size(); i++)
            all += getItem(i);
        return all;
    }
}
