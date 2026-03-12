package ru.app_tests.alchemy.hints_by_ads;

import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import ru.app_tests.alchemy.BaseAlchemyTest;
import ru.page_object_model.alchemy.screens.ads_screen.AdsLocators;
import ru.page_object_model.alchemy.screens.ads_screen.AdsScreen;
import ru.page_object_model.alchemy.screens.home_screen.HomeLocators;
import ru.page_object_model.alchemy.screens.home_screen.HomeScreen;
import ru.page_object_model.alchemy.screens.play_screen.PlayMenuLocators;
import ru.page_object_model.alchemy.screens.play_screen.PlayMenuScreen;
import ru.utils.android_settings.NetworkUtils;
import ru.utils.properties_readers.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class HintsByAdsWatchTest extends BaseAlchemyTest {

    static HomeScreen homeScreen;
    static PlayMenuScreen playMenuScreen;
    static AdsScreen adsScreen;
    static Duration adsWait;

    @BeforeAll
    static void prepare() {
        homeScreen = new HomeScreen();
        playMenuScreen = new PlayMenuScreen();
        adsScreen = new AdsScreen();
        adsWait = Duration.ofSeconds(15);
    }

    @BeforeEach
    void setUpTest() {
        NetworkUtils.enableAll();
    }

    @AfterEach
    void reset() {
        NetworkUtils.enableAll();
    }

    /**
     * @author Арьков Анатолий
     * Покрывает виды реклам из активностей: com.yandex.mobile.ads.common.AdActivity и sg.bigo.ads.api.CompanionAdActivity.
     * для других активностей необходимо разработать логику скипа
     */
    @Test
    void testHintsAbuse() {
        log.info("Старт теста");

        homeScreen.clickElement(HomeLocators.PLAY_BTN_UIS);
        log.info("Открыли меню игры");

        playMenuScreen.clickElement(PlayMenuLocators.HINTS_ADD_BTN_UIS);
        log.info("Открыли сайдбар добавления подсказок");

        playMenuScreen.hintsSideBar.watchAds();

        adsScreen.skipAllAds();

        assertThat(playMenuScreen.hintsSideBar.isHintsAdded())
                .isTrue();
        log.info("Количество подсказок увеличилось на два");
    }
}
