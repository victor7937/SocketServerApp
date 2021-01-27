package by.victor.jwd.server.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static String loadProperty (String fileName, String key){
        Properties properties = new Properties();
        boolean errorKey = false;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(fileName);
            properties.loadFromXML(is);
        } catch (IOException e) {
            errorKey = true;
        }
        if (errorKey) {
           return null;
        }
        return properties.getProperty(key);
    }
}
