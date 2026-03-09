package ru.page_object_model.vkvideo.screens.search_screen.componenets;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorEnum;

@Getter
public enum VideoSearchBarLocators implements LocatorEnum {
    SEARCH_BAR_INPUT_ID(AppiumBy.id("com.vk.vkvideo:id/query"));

    private final By locator;

    VideoSearchBarLocators(By locator) {
        this.locator = locator;
    }
}
