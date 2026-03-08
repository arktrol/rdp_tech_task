package ru.app_tests.vkvideo;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.driver.AndroidDriverProvider;

public class BaseVkVideoTest {
    @BeforeAll
    public static void setUp() {
        // Указываем Selenide, какой провайдер использовать
        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null; // Отключаем установку размера окна (для мобилок не нужно)
        Configuration.timeout = 10000; // Увеличим таймаут для мобильных тестов
        SelenideAppium.launchApp(); // Запускаем приложение
    }

    @Test
    void testVideoPlays() {
        // Здесь будет логика теста
        System.out.println("Тест запущен, приложение открыто");
    }
}
