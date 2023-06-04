package ru.yandex_praktikum.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
}
