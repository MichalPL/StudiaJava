package parserCSV;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Michal on 2015-11-30.
 */
public class ConfigManager {

    private final String PATH = "config.properties";
    private Properties properties;

    public ConfigManager() {
        InputStream inputStream = null;
        properties = new Properties();

        try {
            inputStream = new FileInputStream(PATH);
            properties.load(inputStream);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public int getIntegerProperty(String propertyName) {
        return Integer.parseInt(properties.getProperty(propertyName));
    }
}
