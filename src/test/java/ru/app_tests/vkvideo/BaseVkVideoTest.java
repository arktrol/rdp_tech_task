package ru.app_tests.vkvideo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.driver.AndroidDriverProvider;
import ru.utils.properties_readers.ConfigReader;

@Slf4j
public class BaseVkVideoTest {

    @BeforeAll
    static void setUp() {
        System.setProperty("options.app.package", ConfigReader.getOptionAppPackageVkVideo());
        System.setProperty("options.app.activity", ConfigReader.getOptionAppActivityVkVideo());

        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = ConfigReader.getSelenideConfigTimeout();

        log.info("Setup [VK video app] complete SUCCESS");
    }

    @AfterAll
    static void tearDown(){
        log.info("Tear down SUCCESS");
    }

    @BeforeEach
    void driverLaunch(){
        SelenideAppium.launchApp();
        log.info("Driver launched");
    }
    @AfterEach
    void driverClose(){
        Selenide.closeWebDriver();
        log.info("Driver closed");
    }
}
