package ru.page_object_model.alchemy.screens.home_screen;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.alchemy.base.LocatorAlchemyEnum;

@Getter
public enum HomeLocators implements LocatorAlchemyEnum {
    PLAY_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().text(\"Играть\")"))
    ;

    private final By locator;

    HomeLocators(By locator) {
        this.locator = locator;
    }
}
