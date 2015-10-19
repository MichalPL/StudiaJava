package zad1.Others;

import zad1.Objects.ListOfSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-09.
 */
public class AskAboutSource {

    ListOfSource list;

    public AskAboutSource(ListOfSource l) {
        list = l;
    }

    public void ask() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("===> Wyswietlic kod zrodlowy? Y/N");
        String sourceIndex;
        sourceIndex = br.readLine();
        if (Objects.equals(sourceIndex.toLowerCase(), "y")) {
            System.out.println("===> Podaj index wpisanego linku");
            list.showAllPositions();
            sourceIndex = br.readLine();
            try {
                list.showItem(Integer.parseInt(sourceIndex));
            } catch (Exception ignored) {
                System.out.println("Wystapil blad!");
                //ex.printStackTrace();
            }
        }
        else if (Objects.equals((sourceIndex.toLowerCase()), "exit")) {
            System.exit(0);
        }
    }
}
