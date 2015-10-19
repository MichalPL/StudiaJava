package zad1;

import zad1.Objects.ListOfSource;
import zad1.Objects.SourceHtmlList;
import zad1.Others.AskAboutSource;
import zad1.Others.Conf;
import zad1.Others.Links.AskAboutLinks;
import zad1.Parsers.AbstractParser;
import zad1.Parsers.Parser;
import zad1.Readers.AbstractReader;
import zad1.Readers.ChooseReader;

import java.io.*;
import java.util.Objects;

import static java.lang.System.*;

/**
 * Created by Michal on 2015-10-08.
 * klasa g³ówna
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Conf confFile = new Conf("src\\main\\java\\zad1\\settings.txt");
        boolean repeat = true;
        ListOfSource los = new ListOfSource();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String input;
            while(repeat) {
                out.println("===> Wpisz strone internetowa lub sciezke do pliku .txt");
                input = br.readLine();
                if(Objects.equals(input.toLowerCase(), "default") || Objects.equals(input, "")) {
                    input = confFile.getProperty(1); //domyslny link/plik
                }

                if (Objects.equals(input.toLowerCase(), "exit")) {
                    repeat = false;
                } else {
                    AbstractReader reader = new ChooseReader().UrlOrFile(input);
                    reader.read();
                    SourceHtmlList HtmlSource = reader.htmlSource();
                    AbstractParser parser = new Parser(HtmlSource);
                    parser.abstractParse();
                    AskAboutLinks showLinks = new AskAboutLinks(parser.getSHL(), input);
                    showLinks.showLinks();
                    los.addNewLine(input, HtmlSource);
                    AskAboutSource ask = new AskAboutSource(los);
                    ask.ask();
                }
            }
        } catch (Exception ignored) {
            out.println("Wystapil blad!");
            //ignored.printStackTrace();
        }
    }
}
