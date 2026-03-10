package ru.utils.properties_readers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
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
            log.info("System has property: {} = {}. Return system property",key,systemValue);
            return systemValue;
        }
        String value = properties.getProperty(key);
        log.info("Read property: {} = {}",key,value);
        return value;
    }

    public static String getOptionPlatformName() {
        return getProperty("options.platform.name");
    }

    public static String getOptionsPlatformVersion() {
        return getProperty("options.platform.version");
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

    public static String getOptionAppActivityVkVideo() {
        return getProperty("options.app.activity.vkvideo");
    }

    public static String getOptionAppActivityAlchemy() {
        return getProperty("options.app.activity.alchemy");
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

    public static boolean getOptionNoReset() {
        return Boolean.parseBoolean(getProperty("options.no.reset"));
    }

    public static boolean getOptionFullReset() {
        return Boolean.parseBoolean(getProperty("options.full.reset"));
    }

    public static long getOptionNewCommandTimeout() {
        return Long.parseLong(getProperty("options.new.command.timeout"));
    }

    public static String getAppiumServerUrl() {
        return getProperty("appium.server.url");
    }

    public static long getSelenideConfigTimeout() {
        return Long.parseLong(getProperty("selenide.configuration.timeout"));
    }
}
