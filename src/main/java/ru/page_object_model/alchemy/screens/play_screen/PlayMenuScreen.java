package ru.page_object_model.alchemy.screens.play_screen;

import ru.page_object_model.alchemy.base.BaseAlchemyScreen;
import ru.page_object_model.alchemy.screens.play_screen.components.HintsSideBarComponent;
import ru.page_object_model.alchemy.screens.play_screen.handlers.HintsHandler;

public class PlayMenuScreen extends BaseAlchemyScreen<PlayMenuLocators> {

    public final HintsSideBarComponent hintsSideBar;

    public PlayMenuScreen() {
        this.hintsSideBar = new HintsSideBarComponent();
    }
}
