package ru.page_object_model.vkvideo.base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.appium.SelenideAppiumCollection;
import io.appium.java_client.AppiumBy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

@Slf4j
public abstract class BaseScreen <T extends Enum<T> & LocatorEnum>{

    private final Class<T> enumType;

    protected SelenideElement $(T locator) {
        return SelenideAppium.$(locator.getLocator());
    }

    protected SelenideAppiumCollection $$(T elements) {
        return SelenideAppium.$$(elements.getLocator());
    }

    @SuppressWarnings("unchecked")
    protected BaseScreen() {
        this.enumType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public boolean isAllPresent() {
        return Arrays.stream(enumType.getEnumConstants())
                .allMatch(this::isElementPresent);
    }

    public void clickElement(T element) {
        $(element).shouldBe(visible, enabled, clickable).click();
    }

    public void sendKeys(T element, String text) {
        $(element).shouldBe(visible,enabled).setValue(text);
    }

    public String getText(T element) {
        return $(element).shouldBe(visible).getText();
    }

    public boolean isElementPresent(T element) {
        return $(element).shouldBe(visible, exist).is(exist);
    }

    public boolean isElementVisible(T element) {
        return $(element).shouldBe(visible).is(visible);
    }
}
