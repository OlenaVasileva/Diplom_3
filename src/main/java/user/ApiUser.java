package user;

import constants.TestConstants;
import io.qameta.allure.Step;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class ApiUser {
    public ApiUser() {
        RestAssured.baseURI = TestConstants.BASE_URL;
    }

    @Step("Создание пользователя")
    public Response createNewUserStep(User user) {
        return given()
                .contentType(JSON)
                .body(user)
                .when()
                .post("api/auth/register");
    }

    @Step("Удаление пользователя")
    public Response deleteUserStep(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(JSON)
                .when()
                .delete("/api/auth/user");
    }

    @Step("Авторизация пользователя")
    public Response loginUserStep(UserCreds creds){
        return given()
                .contentType(JSON)
                .body(creds)
                .when()
                .post("/api/auth/login");
    }

}
