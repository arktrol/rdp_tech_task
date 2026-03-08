package ru.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ru.utils.config.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidDriverProvider implements WebDriverProvider {

    @Override
    @NonNull
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.getOptionPlatformName());
        options.setAutomationName(ConfigReader.getOptionAutomationName());
        options.setDeviceName(ConfigReader.getOptionDeviceName());
        options.setAppPackage(ConfigReader.getOptionAppPackage());
        options.setAppActivity(ConfigReader.getOptionAppActivity()); // Или какая у тебя активность (посмотри через adb shell dumpsys)
        options.setNoReset(false); // true - если не надо переустанавливать приложение перед каждым тестом
        options.setFullReset(false);
        options.setNewCommandTimeout(Duration.ofSeconds(300));

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL is invalid", e);
        }
    }
}