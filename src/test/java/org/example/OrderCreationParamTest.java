package org.example;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.pojo.OrderRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@DisplayName("Создание заказа (параметры цветов)")
@RunWith(Parameterized.class)
public class OrderCreationParamTest extends BaseTest {
    private final List<String> colors;

    public OrderCreationParamTest(List<String> colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Цвета: {0}")
    public static Object[][] data() {
        return new Object[][]{
                {Collections.singletonList("BLACK")},
                {Collections.singletonList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Collections.emptyList()}
        };
    }

    @Test
    public void createOrderWithColors() {
        OrderRequest req = new OrderRequest();
        req.setColor(colors);

        Response resp = orderSteps.createOrder(req);
        resp.then().statusCode(201);
        assertNotNull(resp.path("track"));
    }
}