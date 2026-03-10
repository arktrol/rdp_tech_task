package ru.page_object_model.vkvideo.base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.appium.SelenideAppiumCollection;
import org.openqa.selenium.By;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public abstract class BaseScreen <T extends Enum<T> & LocatorEnum>{

    private final Class<T> enumType;

    protected SelenideElement $(T locator) {
        return SelenideAppium.$(locator.getLocator());
    }

    protected SelenideAppiumCollection $$(T elements) {
        return SelenideAppium.$$(elements.getLocator());
    }

    protected SelenideElement $(By locator) {
        return SelenideAppium.$(locator);
    }

    protected SelenideAppiumCollection $$(By elements) {
        return SelenideAppium.$$(elements);
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
        $(element).shouldBe(exist).click();
    }

    public void sendKeys(T element, String text) {
        $(element).shouldBe(exist).setValue(text);
    }

    public String getText(T element) {
        return $(element).shouldBe(exist).getText();
    }

    public boolean isElementPresent(T element) {
        return $(element).shouldBe(exist).exists();
    }

    public boolean isElementVisible(T element) {
        return $(element).shouldBe(visible).is(visible);
    }
}
