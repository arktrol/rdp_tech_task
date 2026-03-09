package ru.page_object_model.vkvideo.screens.search_screen;

import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorEnum;

@Getter
public enum VideoSearchLocators implements LocatorEnum {
    ;

    private final By locator;

    VideoSearchLocators(By locator) {
        this.locator = locator;
    }
}
