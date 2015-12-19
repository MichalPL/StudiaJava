package parserCSV;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Michal on 2015-12-17.
 */
public class FileReader {
    public String read(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
        String line;
        String result = "";
        while(((line = reader.readLine()) != null)) {
            result += line + "\n";
        }
        return result;
    }
}
