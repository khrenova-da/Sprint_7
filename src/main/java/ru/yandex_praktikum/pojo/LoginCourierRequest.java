package ru.yandex_praktikum.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginCourierRequest {
    private String login;
    private String password;


    public static LoginCourierRequest from(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest(createCourierRequest.getLogin(), createCourierRequest.getPassword());
    }

    public static LoginCourierRequest getLoginCourierRequestWithoutLoginFrom(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest(null, createCourierRequest.getPassword());
    }

    public static LoginCourierRequest getLoginCourierRequestWithoutPasswordFrom(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest(createCourierRequest.getLogin(), null);
    }
}
