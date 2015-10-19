package zad1.Readers;

import zad1.Objects.SourceHtmlList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-08.
 * Czyta kod Ÿród³owy strony z podanego url
 */
public class WebPageReader implements AbstractReader {

    private SourceHtmlList HtmlSource;
    String URL;
    public WebPageReader(String url) {
        URL = url;
    }

    @Override
    public SourceHtmlList htmlSource() {
        return HtmlSource;
    }

    private String fixURL(String url) {
        if(url.startsWith("www."))
            return "http://" + url;
        else
            return url;
    }

    @Override
    public void read() throws IOException {
        URL link = new URL(fixURL(URL));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(link.openStream()));
        HtmlSource = new SourceHtmlList();
        String line;

        while (!Objects.equals(line = in.readLine(), null))
            HtmlSource.addNewLine(line);

        in.close();
    }
}
