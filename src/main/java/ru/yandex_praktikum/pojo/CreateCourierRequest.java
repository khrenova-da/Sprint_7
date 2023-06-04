package ru.yandex_praktikum.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourierRequest {
    private String login;
    private String password;
    private String firstName;


}

