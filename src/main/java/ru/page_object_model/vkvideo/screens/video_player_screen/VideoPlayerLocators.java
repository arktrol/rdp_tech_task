package ru.page_object_model.vkvideo.screens.video_player_screen;

import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.openqa.selenium.By;
import ru.page_object_model.vkvideo.base.LocatorEnum;

@Getter
public enum VideoPlayerLocators implements LocatorEnum {
    VIDEO_PROGRESS_TIME_CODE_ID(AppiumBy.id("com.vk.vkvideo:id/current_progress")),
    VIDEO_DISPLAY_ID(AppiumBy.id("com.vk.vkvideo:id/video_display")),
    SEEK_BAR_ACID(AppiumBy.accessibilityId("Перемотка")),
    PLAY_BTN_ACID(AppiumBy.accessibilityId("Воспроизведение")),
    PAUSE_BTN_ACID(AppiumBy.accessibilityId("Пауза")),
    LOADING_CIRCULAR_ID(AppiumBy.id("com.vk.vkvideo:id/progress_view"))
    ;

    private final By locator;

    VideoPlayerLocators(By locator) {
        this.locator = locator;
    }
}
