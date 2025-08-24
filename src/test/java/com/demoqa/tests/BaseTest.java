package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.utils.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class BaseTest {
    @BeforeAll
    static void beforeAll() {

        //общая настройка вебдрайвера
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1020";
        Configuration.pageLoadStrategy = "eager";

        //для подключения к selenoid
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        //активация работы видеозаписи
        DesiredCapabilities capabilities = new DesiredCapabilities();

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-en");

        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
        Configuration.browserCapabilities = capabilities;

        //активация логгирования selenide - для отображения в Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void closeDriver(){
        closeWebDriver();
    }

    @AfterEach
    public void setAttachments() {
        Attach.screenshotAs("screenshot");
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Attach.addVideo();
    }
}
