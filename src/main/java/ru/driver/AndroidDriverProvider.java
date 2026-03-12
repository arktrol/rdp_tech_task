package ru.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ru.utils.properties_readers.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class AndroidDriverProvider implements WebDriverProvider {

    @Override
    @NonNull
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        UiAutomator2Options options = getUiAutomator2Options();

        try {
            return new AndroidDriver(URI.create(ConfigReader.getAppiumServerUrl()).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL is invalid", e);
        }
    }

    private static UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.getOptionPlatformName());
        options.setPlatformVersion(ConfigReader.getOptionsPlatformVersion());
        options.setAutomationName(ConfigReader.getOptionAutomationName());
        options.setDeviceName(ConfigReader.getOptionDeviceName());
        options.setAppPackage(ConfigReader.getOptionAppPackage());
        options.setAppActivity(ConfigReader.getOptionAppActivity());
        options.setNoReset(ConfigReader.getOptionNoReset());
        options.setFullReset(ConfigReader.getOptionFullReset());
        options.setNewCommandTimeout(Duration.ofSeconds(ConfigReader.getOptionNewCommandTimeout()));
        return options;
    }
}