package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.pojo.CourierCreateRequest;
import org.example.pojo.CourierLoginRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

@DisplayName("Логин курьера")
public class CourierLoginTest extends BaseTest {
    private Integer courierId;

    @Before
    public void createCourier() {
        courierSteps.createCourier(new CourierCreateRequest(login, password, firstName))
                .then().statusCode(201);
        courierId = courierSteps.loginCourier(new CourierLoginRequest(login, password)).path("id");
    }

    @After
    public void cleanUp() {
        courierSteps.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    public void loginSuccess() {
        Response resp = courierSteps.loginCourier(new CourierLoginRequest(login, password));
        resp.then().statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Неверный пароль — 404")
    public void wrongPassword() {
        courierSteps.loginCourier(new CourierLoginRequest(login, "wrongPass"))
                .then().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Логин без пароля — 400")
    public void loginWithoutPassword() {
        courierSteps.loginCourier(new CourierLoginRequest(login, ""))
                .then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин без логина — 400")
    public void loginWithoutLogin() {
        courierSteps.loginCourier(new CourierLoginRequest("", password))
                .then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Несуществующий пользователь — 404")
    public void loginNonExistent() {
        courierSteps.loginCourier(new CourierLoginRequest("no_such_user", "123"))
                .then().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}