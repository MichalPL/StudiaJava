package zad1.Readers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Michal on 2015-10-09.
 * Wybiera WebPageReader (url link) lub FileReader (plik)
 */
public class ChooseReader {
    public AbstractReader UrlOrFile(String uof) throws IOException {
        if(uof.contains("http://") || uof.contains("www.") || uof.contains("https://"))
            return new WebPageReader(uof);
        else if(getCode("http://www." + uof) >= 200 && getCode("http://www." + uof) <= 204)
            return new WebPageReader("www." + uof);
        else
            return new FileReader(uof);
    }

    public static int getCode(String url) throws IOException {
        try {
            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            return huc.getResponseCode();
            //return (huc.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception ignored){
            return 404;
        }
    }
}
