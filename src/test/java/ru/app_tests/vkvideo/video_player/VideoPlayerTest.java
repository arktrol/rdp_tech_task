package ru.app_tests.vkvideo.video_player;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.app_tests.vkvideo.BaseVkVideoTest;
import ru.page_object_model.vkvideo.screens.home_screen.HomeScreen;
import ru.page_object_model.vkvideo.screens.search_screen.VideoSearchScreen;
import ru.page_object_model.vkvideo.shared_components.header.HeaderLocators;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VideoPlayerTest extends BaseVkVideoTest {

    static HomeScreen homeScreen;
    static VideoSearchScreen videoSearchScreen;
    static String videoSearchTerm;

    @BeforeAll
    public static void prepare(){
        homeScreen = new HomeScreen();
        videoSearchScreen = new VideoSearchScreen();
        videoSearchTerm = "Тест YouTube 4K МТС";
        log.info("Prepare SUCCESS");
    }

    @Test
    void testVideoPlays() {
        log.info("Тест запущен, приложение открыто");

        assertThat(homeScreen.header.isElementPresent(HeaderLocators.HEADER_ID))
                .isTrue();
        homeScreen.header.clickElement(HeaderLocators.SEARCH_BTN_ACID);

        videoSearchScreen.searchBar.search(videoSearchTerm);

        assertThat(videoSearchScreen.isSearchedPresent(videoSearchTerm))
                .isTrue();

        log.info("Тест завершен");
    }
}
