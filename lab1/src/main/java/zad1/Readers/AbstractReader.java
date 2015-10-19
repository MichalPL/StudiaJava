package zad1.Readers;
import zad1.Objects.SourceHtmlList;

import java.io.IOException;

/**
 * Created by Michal on 2015-10-08.
 */
public interface AbstractReader {
    SourceHtmlList htmlSource();
    void read() throws IOException;
}
