package ru.app_tests.vkvideo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ru.driver.AndroidDriverProvider;
import ru.utils.propertiesReaders.ConfigReader;

@Slf4j
public class BaseVkVideoTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("options.app.package", ConfigReader.getOptionAppPackageVkVideo());
        System.setProperty("options.app.activity", ConfigReader.getOptionAppActivityVkVideo());

        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = ConfigReader.getSelenideConfigTimeout();

        SelenideAppium.launchApp();
        log.info("Setup [VK video app] complete SUCCESS");
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
        log.info("Driver closed.Tear down SUCCESS");
    }
}
