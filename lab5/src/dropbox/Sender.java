package dropbox;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michal on 2016-01-11.
 */
public class Sender {
    private DbxClient client;
    private ConfigManager configManager;
    private AtomicInteger stats = new AtomicInteger(0);

    public Sender() throws IOException {
        configManager = new ConfigManager();
        client  = new DbxClient(new DbxRequestConfig("MyDropbox/1.0", "pl-PL"),
                configManager.getProperty(AppKeys.TOKEN_KEY));
    }

    public void send(String path) throws IOException, DbxException {
        sendFile(new File(path));
    }

    void sendFile(File f) throws IOException, DbxException {
        stats.incrementAndGet();
        try(FileInputStream inputStream = new FileInputStream(f)) {
//            System.out.println("Wysyłanie pliku " + f.getName());
            client.uploadFile("/" + f.getName(), DbxWriteMode.add(), f.length(), inputStream);
//            System.out.println("Plik " + f.getName() + " został wysłany...");
        }
    }

    public int getStats() {
        return stats.getAndSet(0);
    }
}
