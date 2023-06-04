package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex_praktikum.clients.OrderClient;
import ru.yandex_praktikum.dataprovider.OrderProvider;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderRequestTest {

    public static final String BLACK = "BLACK";
    public static final String GRAY = "GRAY";
    private final OrderClient orderClient = new OrderClient();
    public List<String> colors;

    public CreateOrderRequestTest(List<String> colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getColors() {
        return Arrays.asList(new Object[][]{
                {List.of(BLACK, GRAY)},
                {List.of(BLACK)},
                {List.of(GRAY)},
                {Collections.emptyList()},
        });
    }

    @Test
    @DisplayName("Order should be created with different colors")
    public void orderShouldBeCreatedWithDifferentColors() {
        CreateOrderRequest randomCreateOrderRequest = OrderProvider.getRandomCreateOrderRequest();

        randomCreateOrderRequest.setColor(colors);

        orderClient.create(randomCreateOrderRequest).statusCode(201)
                .body("track", Matchers.notNullValue());

    }

}
