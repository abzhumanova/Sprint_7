package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.pojo.CourierCreateRequest;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

@DisplayName("Создание курьера")
public class CourierCreateTest extends BaseTest {
    private Integer courierId;

    @After
    public void tearDown() {
        if (courierId != null) {
            courierSteps.deleteCourier(courierId).then().statusCode(200);
        }
    }

    @Test
    @DisplayName("Курьера можно создать")
    public void courierCanBeCreated() {
        Response response = courierSteps.createCourier(
                new CourierCreateRequest(login, password, firstName)
        );
        response.then().statusCode(201).body("ok", is(true));

        // Сохранение id для последующего удаления
        courierId = courierSteps
                .loginCourier(new org.example.pojo.CourierLoginRequest(login, password))
                .path("id");
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void cantCreateDuplicateCouriers() {
        courierSteps.createCourier(new CourierCreateRequest(login, password, firstName));
        Response second = courierSteps.createCourier(new CourierCreateRequest(login, password, firstName));
        second.then().statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Создание без логина — ошибка 400")
    public void createWithoutLogin() {
        Response resp = courierSteps.createCourier(
                new CourierCreateRequest("", password, firstName)
        );
        resp.then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание без пароля — ошибка 400")
    public void createWithoutPassword() {
        Response resp = courierSteps.createCourier(
                new CourierCreateRequest(login, "", firstName)
        );
        resp.then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}