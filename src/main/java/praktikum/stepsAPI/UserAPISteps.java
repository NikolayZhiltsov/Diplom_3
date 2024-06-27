package praktikum.stepsAPI;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.modelAPI.User;

import static io.restassured.RestAssured.given;

public class UserAPISteps {
    private static final String USER_CREATE = "/api/auth/register";
    private static final String USER_LOGIN = "/api/auth/login";
    private static final String USER_DELETE = "/api/auth/user";

    @Step("Шаг создания пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(USER_CREATE)
                .then();
    }

    @Step("Шаг логина пользователя в систему")
    public ValidatableResponse loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(USER_LOGIN)
                .then();
    }

    @Step("Шаг удаления пользователя")
    public ValidatableResponse deleteUser(User user) {
        return given()
                .header("Authorization", user.getAccessToken())
                .when()
                .delete(USER_DELETE)
                .then();
    }
}
