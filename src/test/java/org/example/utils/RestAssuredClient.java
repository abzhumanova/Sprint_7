package org.example.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class RestAssuredClient {
    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru")  // setBaseUri вместо setBaseUrl
                .setContentType(ContentType.JSON)
                .build();
    }
}