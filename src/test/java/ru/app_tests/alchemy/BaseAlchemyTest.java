package ru.app_tests.alchemy;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.driver.AndroidDriverProvider;
import ru.utils.properties_readers.ConfigReader;

@Slf4j
public class BaseAlchemyTest {
    @BeforeAll
    public static void setUp() {
        System.setProperty("options.app.package", ConfigReader.getOptionAppPackageAlchemy());
        System.setProperty("options.app.activity", ConfigReader.getOptionAppActivityAlchemy());

        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = ConfigReader.getSelenideConfigTimeout();

        log.info("Setup [Alchemy app] complete SUCCESS");
    }

    @AfterAll
    public static void tearDown(){
        log.info("Driver closed.Tear down SUCCESS");
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
