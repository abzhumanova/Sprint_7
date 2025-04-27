package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.pojo.CourierCreateRequest;
import org.example.pojo.CourierLoginRequest;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    private static final String BASE = "https://qa-scooter.praktikum-services.ru";

    @Step("Создать курьера {request.login}")
    public Response createCourier(CourierCreateRequest request) {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Авторизовать курьера {request.login}")
    public Response loginCourier(CourierLoginRequest request) {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Удалить курьера {courierId}")
    public Response deleteCourier(int courierId) {
        return given()
                .baseUri(BASE)
                .when()
                .delete("/api/v1/courier/{id}", courierId);
    }
}