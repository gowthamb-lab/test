package org.example.Repository;

import org.example.model.entity.Menu;
import org.example.model.entity.locations;
import org.springframework.data.repository.CrudRepository;

public interface menuRepository extends CrudRepository<Menu,String> {

    Menu findByDayAndLocality(String day, locations locality);

}
