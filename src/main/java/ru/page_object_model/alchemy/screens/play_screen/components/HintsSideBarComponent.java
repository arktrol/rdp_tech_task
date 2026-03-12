package ru.page_object_model.alchemy.screens.play_screen.components;

import lombok.extern.slf4j.Slf4j;
import ru.page_object_model.alchemy.base.BaseAlchemyScreen;
import ru.page_object_model.alchemy.screens.play_screen.handlers.HintsHandler;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

@Slf4j
public class HintsSideBarComponent extends BaseAlchemyScreen<HintsSideBarLocators> {

    Duration duration = Duration.ofSeconds(10);

    private void saveCurrentHintsCount() {
        HintsHandler.getInstance().setCurrentValue(getCurrentHintsCount());
    }

    private long getCurrentHintsCount(){
        return Long.parseLong($(HintsSideBarLocators.HINTS_COUNT_CONTAINER_UIS).find(HintsSideBarLocators.HINTS_COUNT_VALUE_RELATIVE_CLASS.getLocator()).getText());
    }

    public boolean isHintsAdded(){
        long currentHintsCount = getCurrentHintsCount();
        long prevHintsCount = HintsHandler.getInstance().getCurrentValue();
        log.info("Текущее количество подсказок: {}, значение в хендлере: {}",currentHintsCount,prevHintsCount);
        return currentHintsCount - prevHintsCount == 2;
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
