package ru.stqa.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        properties = new Properties();


        String configFile = System.getProperty("config", "config.properties");

        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream(configFile)) {

            if (input == null) {
                throw new RuntimeException("Config file not found: " + configFile);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Could not load config file: " + configFile, e);
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }
    public static String getSeleniumURL() {
        return properties.getProperty("seleniumServer");
    }

    public static String buildUrl(String endpoint) {
        String baseUrl = getBaseUrl();
        if (endpoint.startsWith("/")) {
            return baseUrl + endpoint;
        } else {
            return baseUrl + "/" + endpoint;
        }
    }
}
