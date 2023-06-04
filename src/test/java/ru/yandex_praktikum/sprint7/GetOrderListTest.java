package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.dataprovider.OrderProvider;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

public class GetOrderListTest {

    private final OrderClient orderClient = new OrderClient();


    @Test
    @DisplayName("GetOrderList should return not null list of orders")
    public void getOrderListShouldReturnCorrectAnswer() {

        orderClient.getOrderList().statusCode(200)
                .body("orders", Matchers.notNullValue());

    }
}
