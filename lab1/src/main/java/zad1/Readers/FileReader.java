package zad1.Readers;

import zad1.Objects.SourceHtmlList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-08.
 * Czyta plik z kodem Ÿród³owym strony z pliku tekstowego
 */
public class FileReader implements AbstractReader {

    private SourceHtmlList HtmlSource;
    String FILE;

    public FileReader(String file) {
        FILE = file;
    }

    @Override
    public SourceHtmlList htmlSource() {
        return HtmlSource;
    }

    @Override
    public void read() throws IOException {
        BufferedReader in = new BufferedReader(new java.io.FileReader(FILE));
        HtmlSource = new SourceHtmlList();

        String line;

        while (!Objects.equals(line = in.readLine(), null))
            HtmlSource.addNewLine(line);

        in.close();
    }
}
