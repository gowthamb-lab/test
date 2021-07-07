package org.example.service;

import org.example.dtos.MenuDto;
import org.example.dtos.locationsDto;
import org.example.model.entity.Menu;
import org.example.model.entity.locations;

public interface menuService {
    MenuDto createMenu(MenuDto menuDto);

    Menu UpdateMenu(String MenuId,Menu menu);

    Menu getMenuByDayAndLocation(String day, locations locality);


}
