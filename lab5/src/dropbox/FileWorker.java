package dropbox;

import java.util.concurrent.Callable;

/**
 * Created by Michal on 2016-01-11.
 */
public class FileWorker implements Callable{
    private Sender sender;
    String path;
    public FileWorker(Sender sender, String path) {
        this.sender = sender;
        this.path = path;
    }

    @Override
    public Object call() throws Exception {
        sender.send(path);
        return null;
    }
}
