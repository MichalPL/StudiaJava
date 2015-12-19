package parserCSV;

/**
 * Created by Michal on 2015-12-07.
 */
public class EmailParser {
    public static boolean isValid(String email) throws IllegalArgumentException {
        if(email == null) {
            throw new IllegalArgumentException("Nieprawidłowy email!");
        }
        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }
}
