package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public Properties prop;
    String filePath = "src/test/resources/config/config.properties";

    /**
     * This method is used to load the properties from config.properties file
     *
     * @return it returns Properties prop object
     */
    public Properties init_prop() {

        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream(filePath); //get file path
            prop.load(ip);
            String url = prop.getProperty("url");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


}
