package ru.yandex_praktikum.sprint7;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.yandex_praktikum.clients.CourierClient;
import ru.yandex_praktikum.dataprovider.CourierProvider;
import ru.yandex_praktikum.pojo.CreateCourierRequest;
import ru.yandex_praktikum.pojo.LoginCourierRequest;

public class LoginCourierRequestTest {

    private final CourierClient courierClient = new CourierClient();
    private Integer id;


    @Test
    @DisplayName("Courier with correct request should login")
    public void courierWithCorrectRequestShouldLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();


        courierClient.create(createCourierRequest);
        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Courier without login in request should not login")
    public void courierWithoutLoginInRequestShouldNotLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();

        courierClient.create(createCourierRequest);

        //логин
        LoginCourierRequest wrongLoginCourierRequest = LoginCourierRequest.getLoginCourierRequestWithoutLoginFrom(createCourierRequest);
        LoginCourierRequest correctLoginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(correctLoginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        courierClient.login(wrongLoginCourierRequest).statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));

    }


    @Test
    @DisplayName("Courier without password in request should not login")
    public void courierWithoutPasswordInRequestShouldNotLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();

        courierClient.create(createCourierRequest);

        //логин
        LoginCourierRequest wrongLoginCourierRequest = LoginCourierRequest.getLoginCourierRequestWithoutPasswordFrom(createCourierRequest);
        LoginCourierRequest correctLoginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(correctLoginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        courierClient.login(wrongLoginCourierRequest).statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));

    }
    @Test
    @DisplayName("Not created courier should not login")
    public void notCreatedCourierShouldNotLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();

        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));

    }


    @Test
    @DisplayName("Courier with incorrect login should not login")
    public void courierWithIncorrectLoginShouldNotLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();


        courierClient.create(createCourierRequest);
        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        loginCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(8));

        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Courier with incorrect password should not login")
    public void courierWithIncorrectPasswordShouldNotLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCourierRequest();


        courierClient.create(createCourierRequest);
        //логин
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(createCourierRequest);

        id = courierClient.login(loginCourierRequest).statusCode(200)
                .extract().jsonPath().get("id");

        loginCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(8));

        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}
