package ru.page_object_model.alchemy.screens.ads_screen;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import ru.page_object_model.alchemy.base.BaseAlchemyScreen;
import ru.page_object_model.alchemy.screens.play_screen.components.HintsSideBarComponent;
import ru.utils.properties_readers.ConfigReader;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Арьков Анатолий
 * Покрывает виды реклам из активностей: com.yandex.mobile.ads.common.AdActivity и sg.bigo.ads.api.CompanionAdActivity.
 * для других активностей необходимо разработать логику скипа
 * на данный момент, если вид рекламы другой, то заходим обратно в игру и запускаем логику просмотра рекламы заново
 * todo
 */
@Slf4j
public class AdsScreen extends BaseAlchemyScreen<AdsLocators> {

    private final HintsSideBarComponent hintsSideBarComponent = new HintsSideBarComponent();

    public void skipAllAds() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        log.info("Текущая активность: {}", driver.currentActivity());
        log.info(driver.getCurrentPackage());
        while (driver.currentActivity().equals("com.yandex.mobile.ads.common.AdActivity") || driver.currentActivity().equals("sg.bigo.ads.api.CompanionAdActivity")) {
            clickSkipAds();
            log.info("Скипнули");
        }
        log.info("Всю рекламу проскипали");
        if (!driver.getCurrentPackage().equals(ConfigReader.getOptionAppPackageAlchemy())) {
            log.info("Текущая активность не соответствует приложению");
            driver.activateApp(ConfigReader.getOptionAppPackageAlchemy());
            log.info("Зашли в приложение");
            if(!hintsSideBarComponent.isHintsAdded()){
                log.info("Подсказки не добавились, смотрим новую рекламу");
                hintsSideBarComponent.watchAds();
            }
        }
    }

    /**
     * @author Арьков Анатолий
     * Метод для поиска кулдауна рекламы и возвращения значения кулдауна
     * не используется
     */
    public int getCountDown(){
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        String source = driver.getPageSource();
        Pattern patternCountDown = Pattern.compile(
                "resource-id=\"com\\.ilyin\\.alchemy:id/inter_text_countdown\"[^>]*text=\"(\\d+)\""
        );
        Matcher matcherCountDown = patternCountDown.matcher(source);

        int waitTime = 15;

        try {
            log.info(matcherCountDown.toString());
            log.info(String.valueOf(source.contains("inter_text_countdown")));
            if (matcherCountDown.find()) {
                waitTime = Integer.parseInt(matcherCountDown.group(1));
                log.info("Кулдаун рекламы: {}", waitTime);
            }
        } catch (IllegalStateException e) {
            log.warn("Кулдаун не найден");
        }
        return waitTime;
    }

    /**
     * @author Арьков Анатолий
     * Метод для ожидания, поиска кнопки скипа и клика по ней в рекламе
     * Было принято решение сделать поиск кнопки скипа через дамп, из-за того,
     * что по локаторам элементы в рекламе ищутся слишком долго (2-3 минуты)
     * работает для всех видов рекламы, где кнопка скипа справа, не ниже половины экрана
     */
    public void clickSkipAds() {
        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        if (driver.currentActivity().equals(ConfigReader.getOptionAppActivityAlchemy())) {
            log.info("Уже вышли из рекламы");
            return;
        }

        int waitTime = 15;

        try {
            if (waitTime > 0) {
                log.info("Ad countdown: {}", waitTime);
                Thread.sleep((waitTime + 2) * 1000L);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        String source = driver.getPageSource();
        Pattern patternBounds = Pattern.compile("bounds=\"\\[(\\d+),(\\d+)]\\[(\\d+),(\\d+)]\"");
        Matcher matcherBounds = patternBounds.matcher(source);

        try {
            while (matcherBounds.find()) {
                int x1 = Integer.parseInt(matcherBounds.group(1));
                int y1 = Integer.parseInt(matcherBounds.group(2));
                int x2 = Integer.parseInt(matcherBounds.group(3));
                int y2 = Integer.parseInt(matcherBounds.group(4));

                if (x1 > 950 && y1 < 1400) {
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

                if (r.getX() > 950 && r.getY() < 1400 && el.isDisplayed()) {
                    el.click();
                    log.info("click");
                    break;
                }
            }
        }
    }
}
