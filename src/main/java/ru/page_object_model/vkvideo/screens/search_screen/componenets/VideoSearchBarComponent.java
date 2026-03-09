package ru.page_object_model.vkvideo.screens.search_screen.componenets;

import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import ru.page_object_model.vkvideo.base.BaseScreen;

import java.util.Map;

public class VideoSearchBarComponent extends BaseScreen<VideoSearchBarLocators> {
    public void search(String searchTerm){
        $(VideoSearchBarLocators.SEARCH_BAR_INPUT_ID).setValue(searchTerm);

        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        driver.executeScript("mobile: performEditorAction", Map.of("action", "search"));
    }
}
