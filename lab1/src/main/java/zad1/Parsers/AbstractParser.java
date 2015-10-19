package zad1.Parsers;

import zad1.Objects.SourceHtmlList;

import java.io.IOException;

/**
 * Created by Michal on 2015-10-08.
 */
public interface AbstractParser {
    void abstractParse() throws IOException;
    SourceHtmlList getSHL();
}
