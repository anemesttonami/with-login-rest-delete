package com.demoqa.utils;

import com.demoqa.dto.LoginDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.api.BookStoreApi.getAccountData;

public class AuthorizationExtention implements BeforeEachCallback {
    private static final String QA_GURU_SESSION = "QA_GURU_SESSION";

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Response accountData = getAccountData(new LoginDto(
                "SupremeFirst", "SupremeFirst1!"// по-хорошему конечно надо откуда то из ,например, Vault или Owner доставать эти значения
        ));

        open("/images/Toolsqa.jpg/" + accountData.path("userId").toString()); // специально открываем что нибудь

        getWebDriver().manage().addCookie(new Cookie("token", accountData.path("token").toString()));
        getWebDriver().manage().addCookie(new Cookie("expires", accountData.path("expires").toString()));
        getWebDriver().manage().addCookie(new Cookie("userID", accountData.path("userId").toString()));
        getWebDriver().manage().addCookie(new Cookie("userName", accountData.path("username").toString()));

    }
}