package zad1.Others;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Michal on 2015-10-09.
 */
public class Conf {
    String path;
    public Conf(String p) {
        path = p;
    }

    public String getProperty(int lineIndex) throws IOException {
        BufferedReader in = new BufferedReader(new java.io.FileReader(path));
        int counter = 1;
        String line, ret = null;
        while((line = in.readLine()) != null)
        {
            if(Objects.equals(counter, lineIndex))
                ret = line;
            counter++;
        }
        in.close();
        return ret;
    }
}
