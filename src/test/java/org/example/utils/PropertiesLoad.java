package org.example.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoad {
    public static String getPropertyValue(String property, InputStream input) throws IOException {
        if (input != null) {
            Properties p = new Properties();
            p.load(input);
            return p.getProperty(property);
        }
        return null;
    }
}
