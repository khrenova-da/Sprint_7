package ru.yandex_praktikum.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex_praktikum.pojo.CreateCourierRequest;

public class CourierProvider {
    public static CreateCourierRequest getRandomCourierRequest() {
        return CreateCourierRequest.builder()
                .login(RandomStringUtils.randomAlphabetic(8))
                .password(RandomStringUtils.randomAlphabetic(8))
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .build();

    }

    public static CreateCourierRequest getRandomCourierWithoutLoginRequest() {
        return CreateCourierRequest.builder()
                .password(RandomStringUtils.randomAlphabetic(8))
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .build();
    }

    public static CreateCourierRequest getRandomCourierWithoutPasswordRequest() {
        return CreateCourierRequest.builder()
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .build();
    }

    public static CreateCourierRequest getRandomCourierWithoutLoginAndPasswordRequest() {
        return CreateCourierRequest.builder()
                .firstName(RandomStringUtils.randomAlphabetic(8))
                .build();
    }
}
