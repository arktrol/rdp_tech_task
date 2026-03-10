package ru.page_object_model.alchemy.screens.play_screen;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.alchemy.base.LocatorAlchemyEnum;

@Getter
public enum PlayMenuLocators implements LocatorAlchemyEnum {
    HINTS_ADD_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(6)"))
    ;

    private final By locator;

    PlayMenuLocators(By locator) {
        this.locator = locator;
    }
}
