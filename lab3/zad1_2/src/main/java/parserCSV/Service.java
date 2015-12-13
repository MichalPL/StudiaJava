package parserCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Michal on 2015-12-07.
 */
public class Service {
    private List<Row> list = new ArrayList<>();

    public Service(String fileName) throws Exception {
        readFromFile(fileName);
    }

    private void readFromFile(String file) throws Exception {
        Set<Row> personSet = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int i=0;
        String line;
        while(((line = reader.readLine()) != null)) {
            String[] cells  = line.split(",");
            if(cells.length != 6)
                throw new Exception("Zły rekord w pliku .csv w linii " + i);
            personSet.add(new Row(cells[0],cells[1],cells[2],cells[3],cells[4], cells[5]));
            i++;
        }

        list = new ArrayList<>(personSet);
    }

    public List<Row> getList() {
        return list;
    }
}
