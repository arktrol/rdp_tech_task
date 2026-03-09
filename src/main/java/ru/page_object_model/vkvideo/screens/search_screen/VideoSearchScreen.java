package ru.page_object_model.vkvideo.screens.search_screen;

import com.codeborne.selenide.appium.SelenideAppium;
import io.appium.java_client.AppiumBy;
import ru.page_object_model.vkvideo.base.BaseScreen;
import ru.page_object_model.vkvideo.screens.search_screen.componenets.VideoSearchBarComponent;
import ru.page_object_model.vkvideo.screens.search_screen.componenets.VideoSearchBarLocators;

import static com.codeborne.selenide.Condition.visible;

public class VideoSearchScreen extends BaseScreen<VideoSearchLocators> {
    public final VideoSearchBarComponent searchBar;

    public VideoSearchScreen() {
        this.searchBar = new VideoSearchBarComponent();
    }

    public boolean isSearchedPresent(String text){
        return SelenideAppium.$(AppiumBy.xpath("//*[@text='" + text + "']")).shouldBe(visible).exists();
    }
}
