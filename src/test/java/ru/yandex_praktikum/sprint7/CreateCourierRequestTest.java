package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.CourierClient;
import ru.yandex_praktikum.dataprovider.CourierProvider;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.LoginCourierRequest;


public class CreateCourierRequestTest {
    private final CourierClient courierClient = new CourierClient();
    private Integer id;

    @Test
    @DisplayName("Courier with correct request should be created")
    public void courierWithCorrectRequestShouldBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();

        //создание
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));
        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

    }

    @Test
    @DisplayName("Two identical couriers should not be created")
    public void twoIdenticalCouriersShouldNotBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();

        //создание первого курьера
        courierClient.create(createCourierRequest);

        //логин, для проверки, что первый курьер создан
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        //создание второго курьера с теми же данными
        courierClient.create(createCourierRequest)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется"));

    }

    @Test
    @DisplayName("Courier without login should not be created")
    public void courierWithoutLoginShouldNotBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierWithoutLoginRequest();

        //создание курьера без логина
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Courier without password should not be created")
    public void courierWithoutPasswordShouldNotBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierWithoutPasswordRequest();

        //создание курьера без логина
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Courier without login and password should not be created")
    public void courierWithoutLoginAndPasswordShouldNotBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierWithoutLoginAndPasswordRequest();

        //создание курьера без логина
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));

    }

    @Test
    @DisplayName("Courier must have unique login")
    public void courierMustHaveUniqueLogin() {
        CreateCourierRequest firstCourierRequest = CourierProvider.getRandomCourierRequest();
        CreateCourierRequest secondCourierRequest = CourierProvider.getRandomCourierRequest();

        secondCourierRequest.setLogin(firstCourierRequest.getLogin());
        //создание курьера без логина
        courierClient.create(firstCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(firstCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        courierClient.create(secondCourierRequest)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется"));

    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}
