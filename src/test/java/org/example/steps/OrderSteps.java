package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.pojo.OrderRequest;

public class OrderSteps {

    private static final String BASE = "https://qa-scooter.praktikum-services.ru";
    private static RequestSpecification requestSpec;

    static {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE)
                .setContentType("application/json")
                .build();
    }

    @Step("Создать заказ")
    public Response createOrder(OrderRequest request) {
        return io.restassured.RestAssured.given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получить список заказов")
    public Response getOrderList() {
        return io.restassured.RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/api/v1/orders");
    }

    @Step("Получить заказ по track {track}")
    public Response getOrderByTrack(int track) {
        return io.restassured.RestAssured.given()
                .spec(requestSpec)
                .queryParam("t", track)
                .when()
                .get("/api/v1/orders/track");
    }

    @Step("Принять заказ id={orderId} курьером {courierId}")
    public Response acceptOrder(int orderId, int courierId) {
        return io.restassured.RestAssured.given()
                .spec(requestSpec)
                .queryParam("courierId", courierId)
                .when()
                .put("/api/v1/orders/accept/{id}", orderId);
    }
}