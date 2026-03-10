# RDP AQA Tech Task — Mobile Automation Framework

Фреймворк для автоматизации мобильных приложений (VK Видео, Алхимия) на Java + Selenide Appium.

## 🔧 Стек технологий
- Java 22
- Selenide Appium 7.14.0
- Appium Java Client 10.0.0
- JUnit 5
- Maven
- Logback / Lombok / AssertJ

## Запуск тестов

### 1. Подготовка окружения
- Установи и запусти **Appium Server** (v2.x) и UIAutomator2
- Создай эмулятор в Android Studio:
    - **Pixel 6a API 34** (Android 14)
    - Архитектура: **x86_64**
    - Системный образ: **Google Play Intel x86_64 Atom System Image**

### 2. Установка приложений
**VK Видео:**
- Установи через Google Play на эмуляторе с Play Market, либо скачай APK под x86_64
**Алхимия:**
- Установи через Google Play на эмуляторе с Play Market, либо скачай APK под x86_64

### 3. Запуск
```bash
mvn clean test
```

## Структура проекта
```
src/
├── main/
│   ├── java/
│   │   ├── ru/
│   │       ├── driver/
│   │       │   └── AndroidDriverProvider.java
│   │       ├── page_object_model/
│   │       │   ├── alchemy/
│   │       │   │   ├── base/
│   │       │   │   │   ├── BaseAlchemyScreen.java
│   │       │   │   │   └── LocatorAlchemyEnum.java
│   │       │   │   ├── screens/
│   │       │   │       ├── ads_screen/
│   │       │   │       │   ├── AdsLocators.java
│   │       │   │       │   └── AdsScreen.java
│   │       │   │       ├── home_screen/
│   │       │   │       │   ├── HomeLocators.java
│   │       │   │       │   └── HomeScreen.java
│   │       │   │       ├── play_screen/
│   │       │   │           ├── components/
│   │       │   │           │   ├── HintsSideBarComponent.java
│   │       │   │           │   └── HintsSideBarLocators.java
│   │       │   │           ├── handlers/
│   │       │   │           │   └── HintsHandler.java
│   │       │   │           ├── PlayMenuLocators.java
│   │       │   │           └── PlayMenuScreen.java
│   │       │   ├── vkvideo/
│   │       │       ├── base/
│   │       │       │   ├── BaseVkVideoScreen.java
│   │       │       │   └── LocatorVkVideoEnum.java
│   │       │       ├── screens/
│   │       │       │   ├── home_screen/
│   │       │       │   │   ├── HomeLocators.java
│   │       │       │   │   └── HomeScreen.java
│   │       │       │   ├── search_screen/
│   │       │       │   │   ├── componenets/
│   │       │       │   │   │   ├── VideoSearchBarComponent.java
│   │       │       │   │   │   └── VideoSearchBarLocators.java
│   │       │       │   │   ├── VideoSearchLocators.java
│   │       │       │   │   └── VideoSearchScreen.java
│   │       │       │   ├── video_player_screen/
│   │       │       │       ├── VideoPlayerLocators.java
│   │       │       │       └── VideoPlayerScreen.java
│   │       │       ├── shared_components/
│   │       │           ├── header/
│   │       │               ├── HeaderComponent.java
│   │       │               └── HeaderLocators.java
│   │       ├── utils/
│   │           ├── android_settings/
│   │           │   └── NetworkUtils.java
│   │           ├── properties_readers/
│   │               └── ConfigReader.java
│   ├── resources/
│       └── config.properties
├── test/
    ├── java/
    │   ├── ru/
    │       ├── app_tests/
    │       │   ├── alchemy/
    │       │   │   ├── hints_by_ads/
    │       │   │   │   └── HintsByAdsWatchTest.java
    │       │   │   └── BaseAlchemyTest.java
    │       │   ├── vkvideo/
    │       │       ├── video_player/
    │       │       │   └── VideoPlayerTest.java
    │       │       └── BaseVkVideoTest.java
    │       ├── base/
    ├── resources/
        └── logback-test.xml
```

## Конфигурация
Основные настройки в `config.properties`:
```properties
options.platform.name=Android
options.platform.version=14
options.automation.name=UiAutomator2
options.device.name=emulator-5554
```

## Логирование
Настроено через Logback (`src/test/resources/logback-test.xml`).

## Примечание
Использовал 14 андроид из-за проблем совместимости с приложением "Алхимия".