package ru.utils.android_settings;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class NetworkUtils {

    private AndroidDriver getDriver() {
        return (AndroidDriver) WebDriverRunner.getWebDriver();
    }

    public void disableNetwork() {
        getDriver().setConnection(new ConnectionState(0));
        log.info("Отключили сеть");
    }

    public void enableWifi() {
        getDriver().setConnection(new ConnectionState(ConnectionState.WIFI_MASK));
    }

    public void enableData() {
        getDriver().setConnection(new ConnectionState(ConnectionState.DATA_MASK));
    }

    public void enableAll() {
        getDriver().setConnection(new ConnectionState(
                ConnectionState.WIFI_MASK | ConnectionState.DATA_MASK
        ));
    }

    public boolean isNetworkEnabled() {
        ConnectionState state = getDriver().getConnection();
        return state.isWiFiEnabled() || state.isDataEnabled();
    }
}
