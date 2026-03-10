package ru.page_object_model.vkvideo.screens.search_screen.componenets;

import ru.page_object_model.vkvideo.base.BaseVkVideoScreen;

public class VideoSearchBarComponent extends BaseVkVideoScreen<VideoSearchBarLocators> {
    public void search(String searchTerm){
        $(VideoSearchBarLocators.SEARCH_BAR_INPUT_ID).setValue(searchTerm);
    }
}
