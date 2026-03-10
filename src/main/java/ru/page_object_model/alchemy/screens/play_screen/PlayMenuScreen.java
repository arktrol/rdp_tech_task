package ru.page_object_model.alchemy.screens.play_screen;

import ru.page_object_model.alchemy.base.BaseAlchemyScreen;
import ru.page_object_model.alchemy.screens.play_screen.components.HintsSideBarComponent;

public class PlayMenuScreen extends BaseAlchemyScreen<PlayMenuLocators> {

    public final HintsSideBarComponent hintsSideBar;

    public PlayMenuScreen() {
        this.hintsSideBar = new HintsSideBarComponent();
    }
}
