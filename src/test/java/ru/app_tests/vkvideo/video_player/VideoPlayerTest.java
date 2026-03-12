package ru.app_tests.vkvideo.video_player;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.app_tests.vkvideo.BaseVkVideoTest;
import ru.page_object_model.vkvideo.screens.home_screen.HomeScreen;
import ru.page_object_model.vkvideo.screens.search_screen.VideoSearchScreen;
import ru.page_object_model.vkvideo.screens.video_player_screen.VideoPlayerLocators;
import ru.page_object_model.vkvideo.screens.video_player_screen.VideoPlayerScreen;
import ru.page_object_model.vkvideo.shared_components.header.HeaderLocators;
import ru.utils.android_settings.NetworkUtils;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class VideoPlayerTest extends BaseVkVideoTest {

    static HomeScreen homeScreen;
    static VideoSearchScreen videoSearchScreen;
    static VideoPlayerScreen videoPlayerScreen;
    static String searchTerm;

    @BeforeAll
    static void prepare(){
        homeScreen = new HomeScreen();
        videoSearchScreen = new VideoSearchScreen();
        videoPlayerScreen = new VideoPlayerScreen();
        searchTerm = "Тест YouTube 4К МТС";
        log.info("Prepare SUCCESS");
    }

    @BeforeEach
    void setUpTest(){
        NetworkUtils.enableAll();
        homeScreen.header.clickElement(HeaderLocators.SEARCH_BTN_ACID);
        log.info("Нажали на кнопку поиска видео в хедере");

        videoSearchScreen.searchBar.search(searchTerm);
        videoSearchScreen.clickSearchSuggestion(searchTerm);
        log.info("Написали в поисковой строке '{}' и нажали по совпадению в выпадающем списке", searchTerm);
    }

    @AfterEach
    void reset(){
        NetworkUtils.enableAll();
    }

    @Test
    void testVideoPlays() {
        log.info("Старт теста");

        assertThat(videoSearchScreen.isSearchedPresent(searchTerm))
                .isTrue();
        log.info("Искомое видео присутствует в списке найденных");

        videoSearchScreen.clickVideo(searchTerm);
        log.info("Кликнули по найденному видео");

        videoPlayerScreen.pauseVideo();
        log.info("Поставили видео на паузу");

        videoPlayerScreen.clickElement(VideoPlayerLocators.SEEK_BAR_ACID);
        log.info("Промотали видео на середину");

        videoPlayerScreen.clickElement(VideoPlayerLocators.PLAY_BTN_ACID);
        log.info("Нажали на кнопку Play");

        assertThat(videoPlayerScreen.isLoadingNotDisappear())
                .isFalse();
        log.info("Загрузка пропала, видео работает");

        log.info("Тест завершен");
    }

    @Test
    void testVideoNotPlays(){
        log.info("Старт теста");

        assertThat(videoSearchScreen.isSearchedPresent(searchTerm))
                .isTrue();
        log.info("Искомое видео присутствует в списке найденных");

        videoSearchScreen.clickVideo(searchTerm);
        log.info("Кликнули по найденному видео");

        NetworkUtils.disableNetwork();

        videoPlayerScreen.pauseVideo();
        log.info("Поставили видео на паузу");

        videoPlayerScreen.clickElement(VideoPlayerLocators.SEEK_BAR_ACID);
        log.info("Промотали видео на середину");

        assertThat(videoPlayerScreen.isLoadingNotDisappear())
                .isTrue();
        log.info("Видео не загрузилось");

        log.info("Тест завершен");
    }
}
