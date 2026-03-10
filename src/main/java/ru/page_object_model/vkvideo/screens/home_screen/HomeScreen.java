package ru.page_object_model.vkvideo.screens.home_screen;

import ru.page_object_model.vkvideo.base.BaseVkVideoScreen;
import ru.page_object_model.vkvideo.shared_components.header.HeaderComponent;

public class HomeScreen extends BaseVkVideoScreen<HomeLocators> {

    public final HeaderComponent header;

    public HomeScreen() {
        this.header = new HeaderComponent();
    }

}
