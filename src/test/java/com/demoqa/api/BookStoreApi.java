package com.demoqa.api;

import com.demoqa.dto.BooksAddDto;
import com.demoqa.dto.IsbnDto;
import com.demoqa.dto.LoginDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreApi {

    public static Response getAccountData(LoginDto dto) {
        Response response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/Account/v1/Login")
                .then()
                .statusCode(200)
                .log().all().extract().response();

        return response;
    }

    public static Response addAnyListOfBooks(Response loginResponse) {

        Response resp = loginResponse;
        BooksAddDto brd = new BooksAddDto(resp.path("userId"), List.of(new IsbnDto("9781449331818"), new IsbnDto("9781449337711")));

        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(brd)
                .header("Authorization", "Bearer " + resp.path("token"))
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .statusCode(201)
                .log().all().extract().response();

        return loginResponse;
    }


    public static Response deleteBooks(Response loginResponse) {

        Response resp = loginResponse;

         given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                 .queryParam("UserId",(String) resp.path("userId"))
                .header("Authorization", "Bearer " + resp.path("token"))
                 .log().all()
                .when()
         .delete("/BookStore/v1/Books")
                .then()
                .statusCode(204)
                .log().all().extract().response();

        return loginResponse;
    }
}
