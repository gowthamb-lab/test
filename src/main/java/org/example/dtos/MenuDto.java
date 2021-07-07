package org.example.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MenuDto {

    String menuId;

    String day;

    String regularMenu;

    private locationsDto locality;

    private List<itemsDto> specialItems;
}
