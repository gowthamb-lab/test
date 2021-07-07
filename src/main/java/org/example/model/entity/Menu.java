package org.example.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Menu {
    @Id
    String menuId;

    String day;

    String regularMenu;

    @OneToOne(fetch= FetchType.EAGER)
    private locations locality;

    @OneToMany(fetch= FetchType.LAZY)
    private List<items> specialItems;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRegularMenu() {
        return regularMenu;
    }

    public void setRegularMenu(String regularMenu) {
        this.regularMenu = regularMenu;
    }

    public locations getLocality() {
        return locality;
    }

    public void setLocality(locations locality) {
        this.locality = locality;
    }

    public List<items> getSpecialItems() {
        return specialItems;
    }

    public void setSpecialItems(List<items> specialItems) {
        this.specialItems = specialItems;
    }
}
