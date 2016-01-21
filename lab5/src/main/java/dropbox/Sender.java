package dropbox;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michal on 2016-01-11.
 */
public class Sender {
    private DbxClient client;
    private AtomicInteger stats = new AtomicInteger(0);

    public Sender(ConfigManager configManager) {
        client = new DbxClient(new DbxRequestConfig("MyDropbox/1.0", "pl-PL"),
                configManager.getProperty(AppKeys.TOKEN_KEY));
    }

    public void send(String path) throws SendFileException {
        sendFile(new File(path));
    }

    void sendFile(File f) throws SendFileException {
        stats.incrementAndGet();
        try(FileInputStream inputStream = new FileInputStream(f)) {
            client.uploadFile("/" + f.getName(), DbxWriteMode.add(), f.length(), inputStream);
        } catch (Exception e) {
            throw new SendFileException(e);
        }
    }

    public int getStats() {
        return stats.getAndSet(0);
    }
}