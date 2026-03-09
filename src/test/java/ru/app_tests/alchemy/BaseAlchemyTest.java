package ru.app_tests.alchemy;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.driver.AndroidDriverProvider;
import ru.utils.propertiesReaders.ConfigReader;

@Slf4j
public class BaseAlchemyTest {
    @BeforeAll
    public static void setUp() {
        System.setProperty("options.app.package", ConfigReader.getOptionAppPackageAlchemy());
        System.setProperty("options.app.activity", ConfigReader.getOptionAppActivityAlchemy());

        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = ConfigReader.getSelenideConfigTimeout();

        SelenideAppium.launchApp();
        log.info("Setup [Alchemy app] complete SUCCESS");
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
        log.info("Driver closed.Tear down SUCCESS");
    }

    @Test
    void testVideoPlays() {
        // Здесь будет логика теста
        System.out.println("Тест запущен, приложение открыто");
        log.info("Тест завершен");
    }
}
