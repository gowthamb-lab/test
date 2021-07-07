package org.example.service.impl;

import org.example.Repository.administratorRepository;
import org.example.model.entity.Administrator;
import org.example.service.administratorService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class administratorServiceImplTest {
//
//
//    @TestConfiguration
//    static class administratorServiceImplTestConfiguration{
//        @Bean
//        public administratorService getService(){
//            return new administratorServiceImpl();
//        }
//    }

//    @Autowired
//    private administratorService serv;
//
//    @MockBean
//    private administratorRepository repo;
//
//    private List<Administrator> adminsList;
//    private Administrator adm=new Administrator();

    @Before
    public void setUp(){
//
//
//        adm.setAdminId("gowtham557");
//        adm.setEmail("gowtham.mandava@gmail.com");
//        adm.setUsername("gowtham117");
//
//        adminsList= Collections.singletonList(adm);
//
//        Mockito.when(repo.findAll()).thenReturn(adminsList);
//        Mockito.when(repo.findById(adm.getAdminId())).thenReturn(Optional.of(adm));
        }

    @After

    public void cleanUp(){
    }

    @Test
    void getAllAdmins() {
//        List<Administrator> allAdmins = serv.getAllAdmins(0,1);
//        Assert.assertEquals("Mismatch between returned and expected results", adminsList,allAdmins );
    }

    @Test
    void createAdmins() {
    }

    @Test
    void updateAdmins() {
    }

    @Test
    void getAdminsById() {
//        Administrator adm1 = serv.getAdminsById("gowtham557");
//        Assert.assertEquals("Mismatch between returned and expected results", adm,adm1 );
    }
}