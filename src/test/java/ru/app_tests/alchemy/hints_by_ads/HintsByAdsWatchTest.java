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

    @Test
    void testHintsAbuse() {
        log.info("Старт теста");

        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();

        log.info(driver.currentActivity());
        log.info(driver.getCurrentPackage());

        homeScreen.clickElement(HomeLocators.PLAY_BTN_UIS);

        playMenuScreen.clickElement(PlayMenuLocators.HINTS_ADD_BTN_UIS);

        playMenuScreen.hintsSideBar.watchAds();

//        driver.currentActivity().contains("AdActivity");
        sleep(16000);

        log.info(driver.currentActivity());
        log.info(driver.getCurrentPackage());

//        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
//                "x", 1000,
//                "y", 250
//        ));

//        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
//                "x", 1000,
//                "y", 800
//        ));

//        =================

//        List<WebElement> elements =
//                driver.findElements(AppiumBy.className("android.widget.ImageView"));
//
//        for (WebElement el : elements) {
//            Rectangle r = el.getRect();
//
//            if (r.getX() > 900 && el.isDisplayed()) {
//                log.info("click");
//                el.click();
//                break;
//            }
//        }

        clickSkipAds();
        log.info("скип");

        sleep(16000);

//        List<WebElement> elements2 =
//                driver.findElements(AppiumBy.className("android.widget.ImageView"));
//
//        for (WebElement el : elements2) {
//            Rectangle r = el.getRect();
//
//            if (r.getX() > 900 && el.isDisplayed()) {
//                log.info("click");
//                el.click();
//                break;
//            }
//        }


        clickSkipAds();
        log.info("скип");

        sleep(16000);


//        List<WebElement> elements3 =
//                driver.findElements(AppiumBy.className("android.widget.ImageView"));
//
//        for (WebElement el : elements3) {
//            Rectangle r = el.getRect();
//
//            if (r.getX() > 900 && el.isDisplayed()) {
//                log.info("click");
//                el.click();
//                break;
//            }
//        }

        clickSkipAds();
        log.info("клоуз");

        assertThat(playMenuScreen.hintsSideBar.isHintsAdded())
                .isTrue();
        log.info("Количество подсказок увеличилось на два");
        sleep(10000);
    }

    public void clickSkipAds() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        if (driver.currentActivity().equals(ConfigReader.getOptionAppActivityAlchemy())) {
            log.info("Уже вышли из рекламы");
            return;
        }
        String source = driver.getPageSource();

        Pattern patternBounds = Pattern.compile("bounds=\"\\[(\\d+),(\\d+)]\\[(\\d+),(\\d+)]\"");
        Matcher matcherBounds = patternBounds.matcher(source);

        Pattern patternCountDown = Pattern.compile(
                "resource-id=\"com\\.ilyin\\.alchemy:id/inter_text_countdown\"[^>]*text=\"(\\d+)\""
        );
        Matcher matcherCountDown = patternCountDown.matcher(source);

        int waitTime = 0;

        try {
            log.info(matcherCountDown.toString());
            if (matcherCountDown.find()) {
                waitTime = Integer.parseInt(matcherCountDown.group(1));
                log.info("Кулдаун рекламы: {}", waitTime);
            }
        }catch (IllegalStateException e){
            log.warn("Кулдаун не найден");
        }


        try {

        if (waitTime > 0) {
            log.info("Ad countdown: {}", waitTime);
            Thread.sleep((waitTime + 2) * 1000L);
        }
        }catch (InterruptedException e){
            log.error(e.getMessage());
        }

        try {
            while (matcherBounds.find()) {
                int x1 = Integer.parseInt(matcherBounds.group(1));
                int y1 = Integer.parseInt(matcherBounds.group(2));
                int x2 = Integer.parseInt(matcherBounds.group(3));
                int y2 = Integer.parseInt(matcherBounds.group(4));

                if (x1 > 900 && y1 < 1600) {
                    int cx = (x1 + x2) / 2;
                    int cy = (y1 + y2) / 2;
                    driver.executeScript("mobile: clickGesture",
                            Map.of("x", cx, "y", cy));
                    log.info("click {} {}", cx, cy);
                    break;
                }
            }
        } catch (IllegalStateException e) {
            log.info("Не нашли кнопку по дампу, ищем кликабельные в диапазоне");
            List<WebElement> elements =
                    driver.findElements(AppiumBy.androidUIAutomator("new UiSelector().clickable(true)"));

            for (WebElement el : elements) {
                Rectangle r = el.getRect();

                if (r.getX() > 900 && r.getY() < 1600 && el.isDisplayed()) {
                    log.info("click");
                    el.click();
                    break;
                }
            }
        }


    }
}
