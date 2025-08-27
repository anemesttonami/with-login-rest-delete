package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.utils.Attach;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@ExtendWith({AllureJunit5.class})
public class BaseTest {
    @BeforeAll
    static void beforeAll() {

        //общая настройка вебдрайвера
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1020";
        Configuration.pageLoadStrategy = "eager";

        RestAssured.baseURI = "https://demoqa.com";
//
//        //для подключения к selenoid
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//
//        //активация работы видеозаписи
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        Configuration.browserCapabilities = capabilities;

        //активация логгирования selenide - для отображения в Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @AfterAll
    static void closeDriver() {
        closeWebDriver();
    }

    @AfterEach
    public void setAttachments() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("screenshot");
            Attach.browserConsoleLogs();
            Attach.pageSource();
            Attach.addVideo();
        }
    }
}
