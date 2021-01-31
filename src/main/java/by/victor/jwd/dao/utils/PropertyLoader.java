package by.victor.jwd.dao.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private final static Logger logger = Logger.getLogger(PropertyLoader.class);

    public static String loadProperty (String fileName, String key){
        Properties properties = new Properties();

        boolean errorKey = false;

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(fileName);
            properties.loadFromXML(is);
        } catch (IOException e) {
            logger.error("Exception while loading properties from " + fileName +
                    " with key " + key + " :"+ e.toString());
            errorKey = true;
        }
        if (errorKey) {
           return null;
        }

        return properties.getProperty(key);
    }
}
