package org.example.service.impl;


import org.example.Repository.administratorRepository;
import org.example.dtos.AdministratorDto;
import org.example.exceptions.AdminNotFoundException;
import org.example.exceptions.AdminServiceException;
import org.example.model.entity.Administrator;
import org.example.service.administratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class administratorServiceImpl implements administratorService {

    @Autowired
    private administratorRepository adminRepo;

    private AdministratorDto convertEntitytoDTO(Administrator admin) {
        AdministratorDto adminDto = new AdministratorDto();
        BeanUtils.copyProperties(admin, adminDto);
        return adminDto;
    }
    private Administrator convertDTOtoEntity(AdministratorDto adminDto){
        Administrator admin = new Administrator();
        BeanUtils.copyProperties(adminDto,admin);
        return admin;
    }

    @Override
    public List<Administrator> getAllAdmins(int pageNo, int size) {

        Pageable paging = PageRequest.of(pageNo, size);
        try{
            List<Administrator> adm= (List<Administrator>) adminRepo.findAll(paging);
            return adm;
        }
        catch(Exception e)
        {
            throw new AdminServiceException("Admin not found",e);
        }
    }

    @Override
    public Boolean createAdmins(AdministratorDto adminDto) {
        try{
            Administrator admin =  convertDTOtoEntity(adminDto);
            Administrator responseEntity = adminRepo.save(admin);
            adminDto= convertEntitytoDTO(responseEntity);
        }
        catch(Exception e)
        {
            System.out.println("order not created");
            throw new AdminServiceException("Requested admin account cannot be created",e);
        }
        return true;
    }

    @Override
    public Administrator updateAdmins(String adminId, Administrator adminD) {
            Optional<Administrator> admin = adminRepo.findById(adminId);
           if(!admin.isPresent())
           {
               throw new AdminNotFoundException("Requested admin account cannot be created");
           }
           return adminRepo.save(adminD);
    }

    @Override
    public Administrator getAdminsById(String adminId)
    {
        Optional<Administrator> admin = adminRepo.findById(adminId);
        if(!admin.isPresent())
        {
            throw new AdminNotFoundException("Requested admin account cannot be created");
        }
        return admin.get();
    }
}
