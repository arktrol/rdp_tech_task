package ru.page_object_model.vkvideo.screens.home_screen;

import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorVkVideoEnum;

@Getter
public enum HomeLocators implements LocatorVkVideoEnum {
    ;

    private final By locator;

    HomeLocators(By locator) {
        this.locator = locator;
    }
}
