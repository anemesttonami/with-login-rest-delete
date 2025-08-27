package com.demoqa.tests;

import com.demoqa.dto.LoginDto;
import com.demoqa.pages.BooksPage;
import com.demoqa.utils.WithLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.demoqa.api.BookStoreApi.*;

public class BookStoreTests extends BaseTest {

    @BeforeEach
    void addListener() {
        System.out.println("BeforeEach отработал");
    }

    @Test
    @Tag("API")
    @DisplayName("Тест, который проверяет , что мы можем успешно : авторизоваться , добавить любую книгу и потом удалить её.")
    void loginThroughApiAndDeleteTest() {
        deleteBooks(addAnyListOfBooks(getAccountData(new LoginDto("SupremeFirst", "SupremeFirst1!"))));
    }

    @Test
    @Tag("UI_API")
    @WithLogin
    @DisplayName("Тест, который проверяет , что мы можем успешно : авторизоваться через API + что мы авторизованы")
    void loginThroughApiAndCheckAuthorization() {
        new BooksPage()
                .openBooksPage()
                .getUserName()
                .should(exist)
                .shouldBe(exactText("SupremeFirst"));

    }

}
