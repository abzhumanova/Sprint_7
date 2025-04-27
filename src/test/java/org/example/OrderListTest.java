package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

@DisplayName("Список заказов")
public class OrderListTest extends BaseTest {

    @Test
    @DisplayName("Тело ответа содержит список заказов")
    public void orderListNotEmpty() {
        Response resp = orderSteps.getOrderList();
        resp.then().statusCode(200)
                .body("orders", not(empty()));
    }
}