package ru.page_object_model.alchemy.screens.play_screen.components;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import ru.page_object_model.alchemy.base.BaseAlchemyScreen;
import ru.page_object_model.alchemy.screens.play_screen.handlers.HintsHandler;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

@Slf4j
public class HintsSideBarComponent extends BaseAlchemyScreen<HintsSideBarLocators> {

    Duration duration = Duration.ofSeconds(10);

    private void saveCurrentHintsCount() {
        long count = Long.parseLong($(HintsSideBarLocators.HINTS_COUNT_CONTAINER_UIS).find(HintsSideBarLocators.HINTS_COUNT_VALUE_RELATIVE_CLASS.getLocator()).getText());
        HintsHandler.getInstance().setCurrentValue(count);
    }

    public void watchAds() {
        saveCurrentHintsCount();

        int maxAttempts = 5;
        int attempt = 1;

        while (attempt <= maxAttempts) {
            try {
                $(HintsSideBarLocators.NO_ADS_BTN_UIS).shouldBe(visible, duration).click();
                attempt++;
            } catch (AssertionError e) {
                attempt++;
            }

            try {
                $(HintsSideBarLocators.WATCH_ADS_BTN_UIS).shouldBe(visible, duration).click();
                log.info("Кликнули по кнопке 'Смотреть'");
                return;
            } catch (AssertionError e) {
                attempt++;
            }
        }

        log.warn("Реклама не появилась после {} попыток", maxAttempts);
    }
}
