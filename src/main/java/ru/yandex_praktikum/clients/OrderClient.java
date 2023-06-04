package ru.yandex_praktikum.clients;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient{

    public ValidatableResponse create(CreateOrderRequest createOrderRequest) {

        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    public ValidatableResponse getOrderList() {

        return given()
                .spec(getSpec())
                .when()
                .get("/api/v1/orders")
                .then();
    }
}
