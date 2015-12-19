package parserCSV;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by Michal on 2015-12-07.
 */
public class PeselParser {
    private static int[] wPESEL = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

    private static List<Integer> convertToIntList(String pesel) {
        return asList(pesel.split("")).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private static int getCheckSum(List<Integer> list) {
        int checksum = 0;
        for (int i = 0; i < 10; i++) {
            checksum += wPESEL[i] * list.get(i);
        }
        checksum = checksum % 10;

        if (checksum != 0) {
            checksum = 10 - checksum;
        }
        return checksum;
    }

    public static boolean isValid(String pesel) throws IllegalArgumentException {
        if (pesel == null || !pesel.matches("[0-9]{11}")) {
            throw new IllegalArgumentException("Nieprawidłowy pesel!");
        }
        List<Integer> pList =  convertToIntList(pesel);
        int controlSum = pList.get(10);
        return (getCheckSum(pList)==controlSum);
    }

}
