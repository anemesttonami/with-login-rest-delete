package com.demoqa.tests;

import com.demoqa.dto.LoginDto;
import org.junit.jupiter.api.Test;

import static com.demoqa.api.BookStoreApi.*;

public class BookStoreTests extends BaseTest {

    @Test
    void loginThroughApiAndDeleteTest() {
        deleteBooks(addAnyListOfBooks(getAccountData(new LoginDto("SupremeFirst", "SupremeFirst1!"))));
    }

}
