package ru.page_object_model.vkvideo.screens.home_screen;

import ru.page_object_model.vkvideo.base.BaseScreen;
import ru.page_object_model.vkvideo.shared_components.header.HeaderComponent;

public class HomeScreen extends BaseScreen<HomeLocators> {

    public final HeaderComponent header;

    public HomeScreen() {
        this.header = new HeaderComponent();
    }

}
