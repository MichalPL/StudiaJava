import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Michal on 2015-11-29.
 */
public class Configuration {

    private final String PATH = "config.properties";
    private Properties properties;

    public Configuration() {
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

    public boolean showCategories() {
        return Boolean.parseBoolean(properties.getProperty("SHOW_CATEGORIES"));
    }


    public boolean showNotDone() {
        return Boolean.parseBoolean(properties.getProperty("SHOW_NOTDONE"));
    }

    public String getCategory() {
        return properties.getProperty("CATEGORY");
    }

}
