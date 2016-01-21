package dropbox;

/**
 * Created by Michal on 2016-01-21.
 */
public class ReadFileException extends Exception {
    public ReadFileException(String message) { super("Blad czytania pliku: " + message); }
    public ReadFileException(String message, Throwable cause) { super("Blad czytania pliku: " + message, cause); }
    public ReadFileException(Throwable cause) { super(cause); }
}
