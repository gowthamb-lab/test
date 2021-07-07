package org.example.service;

import org.example.dtos.AdministratorDto;
import org.example.model.entity.Administrator;

import java.util.List;

public interface administratorService {

List<Administrator> getAllAdmins(int pageno, int size);

Boolean createAdmins(AdministratorDto admin);

Administrator updateAdmins(String adminId,Administrator adminDto);

Administrator getAdminsById(String adminId);







}
