package ru.app_tests.alchemy.hints_by_ads;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.app_tests.alchemy.BaseAlchemyTest;
import ru.page_object_model.alchemy.screens.ads_screen.AdsLocators;
import ru.page_object_model.alchemy.screens.ads_screen.AdsScreen;
import ru.page_object_model.alchemy.screens.home_screen.HomeLocators;
import ru.page_object_model.alchemy.screens.home_screen.HomeScreen;
import ru.page_object_model.alchemy.screens.play_screen.PlayMenuLocators;
import ru.page_object_model.alchemy.screens.play_screen.PlayMenuScreen;
import ru.utils.android_settings.NetworkUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.sleep;

@Slf4j
public class HintsByAdsWatchTest extends BaseAlchemyTest {

    static HomeScreen homeScreen;
    static PlayMenuScreen playMenuScreen;
    static AdsScreen adsScreen;
    static Duration adsWait;

    @BeforeAll
    static void prepare(){
        homeScreen = new HomeScreen();
        playMenuScreen = new PlayMenuScreen();
        adsScreen = new AdsScreen();
        adsWait = Duration.ofSeconds(15);
    }

    @BeforeEach
    void setUpTest(){
        NetworkUtils.enableAll();
    }

    @AfterEach
    void reset(){
        NetworkUtils.enableAll();
    }

    @Test
    void testHintsAbuse(){
        log.info("Старт теста");

        homeScreen.clickElement(HomeLocators.PLAY_BTN_UIS);

        playMenuScreen.clickElement(PlayMenuLocators.HINTS_ADD_BTN_UIS);

        playMenuScreen.hintsSideBar.watchAds();

        sleep(15000);
        adsScreen.clickElement(AdsLocators.SKIP_ARROW_BTN_UIS);
        sleep(15000);

        adsScreen.clickElement(AdsLocators.SKIP_ARROW_BTN_UIS);
        sleep(15000);

        adsScreen.clickElement(AdsLocators.CLOSE_FINISH_BTN_UIS,adsWait);

//        sleep(10000);
    }
}
