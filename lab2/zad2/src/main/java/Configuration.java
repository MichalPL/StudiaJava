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

    public String getPropertyAsString(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public boolean gePropertytAsBool(String propertyName) {
        return Boolean.parseBoolean(properties.getProperty(propertyName));
    }

}
