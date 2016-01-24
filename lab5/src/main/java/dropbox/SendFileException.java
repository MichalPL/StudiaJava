package dropbox;

/**
 * Created by Michal on 2016-01-21.
 */
public class SendFileException extends Exception {
    public SendFileException() { super(); }
    public SendFileException(String message) { super("Blad wysylania: " + message); }
    public SendFileException(String message, Throwable cause) { super("Blad wysylania: " + message, cause); }
    public SendFileException(Throwable cause) { super("Blad wysylania: " + cause); }
}
