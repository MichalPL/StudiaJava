package parserCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Michal on 2015-12-07.
 */
public class Service {
    private List<Row> list = new ArrayList<>();
    private parserCSV.FileReader freader;
    public Service(String fileName) throws IOException {
        freader = new parserCSV.FileReader();
        readFromFile(fileName);
    }

    public void readFromFile(String file) throws IOException {
        Set<Row> personSet = new HashSet<>();
        int i=0;
        String[] lines = freader.read(file).split("\n");
        for (String line : lines) {
            String[] cells = line.split(",");
            if (cells.length != 6)
                throw new IOException("Zły rekord w pliku .csv w linii " + i);
            personSet.add(new Row(cells[0], cells[1], cells[2], cells[3], cells[4], cells[5]));
        }
        list = new ArrayList<>(personSet);
    }

    public List<Row> getList() {
        return list;
    }
}
