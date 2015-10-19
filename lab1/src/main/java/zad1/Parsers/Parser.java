package zad1.Parsers;

import zad1.Objects.SourceHtmlList;

import java.io.IOException;

/**
 * Created by Michal on 2015-10-08.
 * Wyszukuje linki w podanym kodzie Ÿród³owym
 */
public class Parser implements AbstractParser {
    private final SourceHtmlList HtmlSource;
    SourceHtmlList urlList;
    String linkExpression = "a href=";

    public Parser(SourceHtmlList htmlSource) {
        HtmlSource = htmlSource;
    }

    @Override
    public void abstractParse() throws IOException {
        urlList = new SourceHtmlList();
        int counter = 0;
        for(int i = 0; i < HtmlSource.getSize(); i++) {
            if (HtmlSource.getItem(i).contains(linkExpression)) {
                urlList.addNewLine(findUrl(HtmlSource.getItem(i)));
                counter++;
            }
        }
        System.out.println(linkCount(counter));
    }

    private String findUrl(String text)
    {
        int start = text.indexOf(linkExpression) + linkExpression.length() + 1;
        int end = text.indexOf("\"", start + 1);
        return text.substring(start, end);
    }

    public String linkCount(int c){
        return "Znalezionych linkow: " + c;
    }

    public SourceHtmlList getSHL() {
        return urlList;
    }

}