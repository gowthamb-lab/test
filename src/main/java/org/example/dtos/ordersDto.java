package org.example.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ordersDto {

    String orderId;

    Double orderPrice;

    Date date;

    private locationsDto locality;



}
