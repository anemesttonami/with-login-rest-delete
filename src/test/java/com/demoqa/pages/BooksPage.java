package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Data
public class BooksPage {
    private final SelenideElement userName = $("#userName-value");

    @Step("Открываем страницу books")
    public BooksPage openBooksPage() {
        open("/books");
        return this;
    }

}
