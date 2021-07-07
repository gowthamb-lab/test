package org.example.service;

import org.example.dtos.locationsDto;
import org.example.model.entity.Administrator;
import org.example.model.entity.locations;

import java.util.List;

public interface locationsService {
    List<locations> getAllLocations(int pageNo, int pageSize);

    Boolean createLocation(locationsDto locality);

    locations updateLocation(String locationId,locations locality);

    locations getLocationById(String locationId);

    Boolean deleteLocation(String locationId);


}
