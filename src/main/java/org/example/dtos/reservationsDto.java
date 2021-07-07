package org.example.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class reservationsDto {

    String reservationId;

    String CustomerName;

    String email;

    String mobileNumber;

    private locationsDto locality;


}
