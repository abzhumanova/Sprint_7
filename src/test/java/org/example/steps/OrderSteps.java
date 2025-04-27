package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.pojo.OrderRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    private static final String BASE = "https://qa-scooter.praktikum-services.ru";

    @Step("Создать заказ")
    public Response createOrder(OrderRequest request) {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получить список заказов")
    public Response getOrderList() {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders");
    }

    @Step("Получить заказ по track {track}")
    public Response getOrderByTrack(int track) {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .queryParam("t", track)
                .when()
                .get("/api/v1/orders/track");
    }

    @Step("Принять заказ id={orderId} курьером {courierId}")
    public Response acceptOrder(int orderId, int courierId) {
        return given()
                .baseUri(BASE)
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .when()
                .put("/api/v1/orders/accept/{id}", orderId);
    }
}