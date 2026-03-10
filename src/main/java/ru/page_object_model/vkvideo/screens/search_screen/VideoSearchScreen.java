package ru.page_object_model.vkvideo.screens.search_screen;

import io.appium.java_client.AppiumBy;
import ru.page_object_model.vkvideo.base.BaseVkVideoScreen;
import ru.page_object_model.vkvideo.screens.search_screen.componenets.VideoSearchBarComponent;

import static com.codeborne.selenide.Condition.exist;

public class VideoSearchScreen extends BaseVkVideoScreen<VideoSearchLocators> {
    public final VideoSearchBarComponent searchBar;

    public VideoSearchScreen() {
        this.searchBar = new VideoSearchBarComponent();
    }

    public boolean isSearchedPresent(String searchTerm) {
        return $(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + searchTerm + "\")")).shouldBe(exist).exists();
    }

    public void clickVideo(String searchTerm) {
        $(AppiumBy.xpath(
                "//android.widget.LinearLayout[.//android.widget.TextView[@text='" +
                        searchTerm +
                        "']]//android.widget.FrameLayout[@resource-id='com.vk.vkvideo:id/video_display']//android.view.View"
        )).click();
    }

    public void clickSearchSuggestion(String searchTerm) {
        $(AppiumBy.xpath(
                "//android.widget.Button[.//android.widget.TextView[@text='" +
                searchTerm + "']]")).click();
    }
}
