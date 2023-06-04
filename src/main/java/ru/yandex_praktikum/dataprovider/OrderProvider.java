package ru.yandex_praktikum.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import ru.yandex_praktikum.pojo.CreateOrderRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderProvider {

    public static CreateOrderRequest getRandomCreateOrderRequest() {
        return CreateOrderRequest.builder()
                .address(RandomStringUtils.randomAlphabetic(8))
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .comment(RandomStringUtils.randomAlphabetic(8))
                .lastName(RandomStringUtils.randomAlphabetic(8))
                .rentTime(RandomUtils.nextInt())
                .deliveryDate(LocalDate.now().toString())
                .metroStation(RandomStringUtils.randomAlphabetic(8))
                .build();
    }
}
