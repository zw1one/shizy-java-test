package com.shizy.utils.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties getPropertiesInProject(String path) {
        Properties properties = null;
        try {
            properties = new Properties();
            InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Properties getPropertiesInDir(String path) {
        Properties properties = null;
        try {
            properties = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            ;
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
