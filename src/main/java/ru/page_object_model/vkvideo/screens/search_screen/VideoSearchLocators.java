package ru.page_object_model.vkvideo.screens.search_screen;

import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorVkVideoEnum;

@Getter
public enum VideoSearchLocators implements LocatorVkVideoEnum {
    ;

    private final By locator;

    VideoSearchLocators(By locator) {
        this.locator = locator;
    }
}
