package ru.page_object_model.alchemy.screens.play_screen.handlers;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class HintsHandler {
    private static HintsHandler instance;
    private long prevValue = -1;
    private long currentValue = -1;

    private HintsHandler() {
    }

    public static HintsHandler getInstance() {
        if (instance == null) {
            instance = new HintsHandler();
        }
        return instance;
    }

    public void setValuesEquals(long everyValue){
        prevValue =everyValue;
        currentValue = everyValue;
    }

    public void setCurrentValue(long currentValue) {
        this.prevValue = this.currentValue;
        this.currentValue = currentValue;
        log.info("Сохранено количество подсказок: {}. Предыдущее значение: {}",currentValue, prevValue);
    }
}
