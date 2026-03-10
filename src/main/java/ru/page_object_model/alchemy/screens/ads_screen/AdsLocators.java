package ru.page_object_model.alchemy.screens.ads_screen;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.alchemy.base.LocatorAlchemyEnum;

@Getter
public enum AdsLocators implements LocatorAlchemyEnum {
    SKIP_ARROW_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")),
    CLOSE_FINISH_BTN_UIS(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)"))
    ;

    private final By locator;

    AdsLocators(By locator) {
        this.locator = locator;
    }
}
