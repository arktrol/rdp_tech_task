package ru.page_object_model.vkvideo.screens.home_screen;

import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorEnum;

@Getter
public enum HomeLocators implements LocatorEnum {
    ;

    private final By locator;

    HomeLocators(By locator) {
        this.locator = locator;
    }
}
