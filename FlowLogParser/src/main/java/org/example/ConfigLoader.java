package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigLoader {

    private final Properties properties;

    public ConfigLoader(String configFilePath) {
        properties = new Properties();
        try {
            properties.load(ConfigLoader.class.getClassLoader().getResourceAsStream(configFilePath));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to load config file: " + configFilePath, ex);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
