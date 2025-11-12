package ru.stqa.mantis.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {

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

    public static String getJamesWorkingDir() {
        var dir = properties.getProperty("james.workingdir");
        if (!dir.endsWith("/") && !dir.endsWith("\\")) {
            dir += "/";
        }
        return dir;
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