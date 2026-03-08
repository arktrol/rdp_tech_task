package ru.utils.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }
        return properties.getProperty(key);
    }

    public static String getOptionPlatformName() {
        return getProperty("options.platform.name");
    }

    public static String getOptionAutomationName() {
        return getProperty("options.automation.name");
    }

    public static String getOptionDeviceName() {
        return getProperty("options.device.name");
    }

    public static String getOptionAppActivity() {
        return getProperty("options.app.activity");
    }

    public static String getOptionAppPackage() {
        return getProperty("options.app.package");
    }

    public static String getOptionAppPackageVkVideo() {
        return getProperty("options.app.package.vkvideo");
    }

    public static String getOptionAppPackageAlchemy() {
        return getProperty("options.app.package.alchemy");
    }

    public static String getOptionNoReset() {
        return getProperty("options.no.reset");
    }

    public static String getOptionFullReset() {
        return getProperty("options.full.reset");
    }

}

class Main{
    public static void main(String[] args) {
        System.setProperty("options.platform.name","IOS");
        System.out.println(ConfigReader.getOptionPlatformName());
    }
}
