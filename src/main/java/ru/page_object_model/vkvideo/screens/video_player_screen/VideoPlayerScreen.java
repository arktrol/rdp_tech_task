package ru.page_object_model.vkvideo.screens.video_player_screen;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import ru.page_object_model.vkvideo.base.BaseScreen;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;

@Slf4j
public class VideoPlayerScreen extends BaseScreen<VideoPlayerLocators> {

    private static final long LoadingTimeout = 10;

    public void pauseVideo() {
        SelenideElement element = $(VideoPlayerLocators.VIDEO_DISPLAY_ID).shouldBe(exist, visible);
        element.click();
        element.click();
    }

    public boolean isSeekBarValueNotDefault() {
        double defaultValue = 0.0;
        double currentValue = Double.parseDouble(getText(VideoPlayerLocators.SEEK_BAR_ACID));
        log.info("default seek bar value: {}, current seek bar value: {}", defaultValue, currentValue);
        return defaultValue != currentValue;
    }

    public boolean isLoadingNotDisappear() {
        try {
            $(VideoPlayerLocators.LOADING_CIRCULAR_ID).should(not(disappear), Duration.ofSeconds(LoadingTimeout));
            log.info("Колесо загрузки не исчезло");
            return true;
        } catch (AssertionError e) {
            log.error("Колесо загрузки исчезло после {} секунд", LoadingTimeout);
            return false;
        }
    }

    public void seekUntilBufferingStarts() {
        SelenideElement display = $(VideoPlayerLocators.VIDEO_DISPLAY_ID);
        SelenideElement loadingSpinner = $(VideoPlayerLocators.LOADING_CIRCULAR_ID);

        int maxAttempts = 10;
        int attempt = 1;

        while (!loadingSpinner.is(visible) && attempt <= maxAttempts) {
            display.click(ClickOptions.withOffset(
                    display.getSize().width - 300, display.getSize().height / 2)
            ).doubleClick();
            try {
                loadingSpinner.shouldBe(visible, Duration.ofMillis(500));
                log.info("Появилось колесо загрузки после {} двойных кликов", attempt);
                break;
            } catch (AssertionError e) {
                log.warn("Колесо загрузки не появилось после {} двойных кликов", attempt);
            }
            attempt++;
        }

        if (!loadingSpinner.is(visible)) {
            log.warn("Не удалось вызвать буферизацию после {} двойных кликов", maxAttempts);
        }
    }
}
