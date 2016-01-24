package crawler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Michal on 2016-01-11.
 */
public class ConfigManager {
    Properties prop = new Properties();
    public ConfigManager() throws ReadFileException {
        try(InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch(Exception e) {
            throw new ReadFileException(e);
        }
    }

    public String getProperty(String s)
    {
        return prop.getProperty(s);
    }

    public void setProperty(String key, String string) {
        prop.setProperty(key, string);
    }
}
