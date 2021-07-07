package org.example.service.impl;

import org.example.Repository.locationRepository;
import org.example.dtos.locationsDto;
import org.example.exceptions.AdminNotFoundException;
import org.example.exceptions.locationNotFoundException;
import org.example.exceptions.locationServiceException;
import org.example.model.entity.locations;
import org.example.service.locationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class locationsServiceImpl implements locationsService {

    @Autowired
    private locationRepository localRepo;

    private locationsDto convertEntitytoDTO(locations locality) {
        locationsDto localityDto = new locationsDto();
        BeanUtils.copyProperties(locality, localityDto);
        return localityDto;
    }
    private locations convertDTOtoEntity(locationsDto localityDto){
        locations locality = new locations();
        BeanUtils.copyProperties(localityDto,locality);
        return locality;
    }


    @Override
    public List<locations> getAllLocations(int pageNo,int size) {

        Pageable paging = PageRequest.of(pageNo, size);

        try{
            System.out.println("what ios this");
            System.out.println(localRepo.findAll());
            List<locations> adm= (List<locations>) localRepo.findAll();
            return adm;
        }
        catch(Exception e)
        {
            System.out.println("location not found");
            throw new locationServiceException("There are no locations stored",e);
        }
    }

    @Override
    public Boolean createLocation(locationsDto localityDto) {
        try{
            locations locality =  convertDTOtoEntity(localityDto);
            locations responseEntity = localRepo.save(locality);
            localityDto= convertEntitytoDTO(responseEntity);
        }
        catch(Exception e)
        {
            System.out.println("order not created");
            throw new locationServiceException("Requested location account cannot be created",e);
        }
        return true;
    }

    @Override
    public locations updateLocation(String locationId, locations locality) {
        Optional<locations> loc = localRepo.findById(locationId);
        if(!loc.isPresent())
        {
            throw new AdminNotFoundException("Requested admin account cannot be created");
        }
        return localRepo.save(locality);
    }

    @Override
    public locations getLocationById(String locationId) {
        Optional<locations> loc = localRepo.findById(locationId);
        if(!loc.isPresent())
        {
            throw new AdminNotFoundException("Requested admin account cannot be created");
        }
        return loc.get();
    }

    @Override
    public Boolean deleteLocation(String locationId) {
        Optional<locations> loc = localRepo.findById(locationId);
        if(!loc.isPresent())
        {
            throw new locationNotFoundException("Requested location cannot be created");
        }
        localRepo.delete(loc.get());
        return true;
    }
}
