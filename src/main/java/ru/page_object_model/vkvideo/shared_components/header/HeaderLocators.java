package ru.page_object_model.vkvideo.shared_components.header;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorEnum;

@Getter
public enum HeaderLocators implements LocatorEnum {
    HEADER_ID(AppiumBy.id("com.vk.vkvideo:id/outer_header_container")),
    SEARCH_BTN_ACID(AppiumBy.accessibilityId("Поиск")),
    ;

    private final By locator;

    HeaderLocators(By locator) {
        this.locator = locator;
    }
}
