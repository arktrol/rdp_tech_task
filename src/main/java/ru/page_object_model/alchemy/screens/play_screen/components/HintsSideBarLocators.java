package ru.page_object_model.alchemy.screens.play_screen.components;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.alchemy.base.LocatorAlchemyEnum;

@Getter
public enum HintsSideBarLocators implements LocatorAlchemyEnum {
    HINTS_COUNT_CONTAINER_UIS(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(9)")),
    HINTS_COUNT_VALUE_RELATIVE_CLASS(AppiumBy.className("android.widget.TextView")),
    WATCH_ADS_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().text(\"Смотреть\")")),
    NO_ADS_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().text(\"Нет рекламы\")")),
    ADS_LOADING_UIS(AppiumBy.androidUIAutomator("new UiSelector().text(\"Загрузка\")"))
    ;

    private final By locator;

    HintsSideBarLocators(By locator) {
        this.locator = locator;
    }
}
