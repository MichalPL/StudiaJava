package zad1.Others.Links;

import zad1.Objects.SourceHtmlList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-09.
 */
public class AskAboutLinks {
    SourceHtmlList list;
    String domain;
    public AskAboutLinks(SourceHtmlList hlist, String d) {
        list = hlist;
        domain = d;
    }

    public void showLinks() throws IOException {
        System.out.println("Chcesz wyswietlic linki? Y/N");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        input = br.readLine().toLowerCase();
        CheckLink cl;
        if (input.equals("y")) {
            System.out.println("Chcesz wyswietlic linki tylko wewnterzne linki? Y/N");
            input = br.readLine().toLowerCase();
            if (input.equals("y")) {
                for (int i = 0; i < list.getSize(); i++) {
                    if (new CheckLink(list.getItem(i), domain).CheckDomainLink()) {
                        System.out.println(list.getItem(i));
                    }
                }
            }
            else if (input.equals("exit")) {
                System.exit(0);
            }
            else {
                for (int i = 0; i < list.getSize(); i++)
                    System.out.println(list.getItem(i));
            }
        }
        else if (input.equals("exit")) {
            System.exit(0);
        }
    }
}
