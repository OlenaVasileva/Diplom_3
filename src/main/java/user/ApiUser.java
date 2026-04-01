package user;

import io.qameta.allure.Step;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class ApiUser {
    public ApiUser() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru/";
    }

    @Step("Создание пользователя")
    public Response createNewUserStep(User user) {
        return given()
                .contentType(JSON)
                .body(user)
                .when()
                .post("api/auth/register");
    }

}
