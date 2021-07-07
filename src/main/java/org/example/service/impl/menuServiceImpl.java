package org.example.service.impl;

import org.example.Repository.menuRepository;
import org.example.dtos.MenuDto;
import org.example.exceptions.AdminNotFoundException;
import org.example.exceptions.menuServiceException;
import org.example.model.entity.Menu;
import org.example.model.entity.locations;
import org.example.service.menuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class menuServiceImpl implements menuService {

    private MenuDto convertEntitytoDTO(Menu menu) {
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu, menuDto);
        return menuDto;
    }
    private Menu convertDTOtoEntity(MenuDto menuDto){
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        return menu;
    }


    @Autowired
    private menuRepository menuRepo;
    @Override
    public MenuDto createMenu(MenuDto menuDto) {
        try{
            Menu menu =  convertDTOtoEntity(menuDto);
            Menu responseEntity = menuRepo.save(menu);
            menuDto= convertEntitytoDTO(responseEntity);
        }
        catch(Exception e)
        {
            System.out.println("order not created");
            throw new menuServiceException("Requested menu account cannot be created",e);
        }
        return menuDto;
    }
    @Override
    public Menu UpdateMenu(String MenuId, Menu menu) {

        Optional<Menu> men = menuRepo.findById(MenuId);
        if(!men.isPresent())
        {
            throw new AdminNotFoundException("Requested admin account cannot be created");
        }
        return menuRepo.save(menu);
    }

    @Override
    public Menu getMenuByDayAndLocation(String day, locations locality) {

        return menuRepo.findByDayAndLocality(day,locality);


    }
}
