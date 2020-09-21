package com.everysports.user.service;

import com.everysports.user.domain.Block;
import com.everysports.user.domain.EUser;
import com.everysports.user.repository.BlockRepository;
import com.everysports.user.repository.EUserRepository;
import com.sun.javafx.runtime.eula.Eula;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EUserServiceTest {
    @Autowired
    private EUserService eUserService;
    @Autowired
    private EUserRepository eUserRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getEUserExcludeBlocks(){
        givenEUsers();

        List<EUser> result = eUserService.getEUsers();

        //System.out.println(result);

        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest() {
        givenEUsers();

        List<EUser> result = eUserRepository.findAll();

        result.forEach(System.out::println);

        EUser eUser = result.get(3);
        eUser.getBlock().setStartDate(LocalDate.now());
        eUser.getBlock().setEndDate(LocalDate.now());

        eUserRepository.save(eUser);
        eUserRepository.findAll().forEach(System.out::println);

//        eUserRepository.delete(eUser);
//        eUserRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        eUser.setBlock(null);
        eUserRepository.save(eUser);
        eUserRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void getEUser(){
        givenEUsers();

        EUser eUser = eUserService.getEUser(5L);

        System.out.println(eUser);
    }

    private void givenEUsers() {
        givenEUser(1000L,"koalakid154@gmail.com","martin", true, new Date(),"010-4110-6893", 150);
        givenEUser(1001L,"koalakid153@gmail.com","martin3", true, new Date(),"010-4110-6894", 153);
        givenEUser(1002L,"koalakid152@gmail.com","martin2", true, new Date(),"010-4110-6895", 152);
        givenBlockEUser(1003L,"koalakid151@gmail.com","martin", true, new Date(),"010-4110-6896", 151);
    }

    private void givenEUser(Long id,String email,String name, boolean gender,Date birthday, String phone, Integer point) {
        eUserRepository.save(new EUser(id,email,name,gender,birthday,phone,point));
    }

    private void givenBlockEUser(Long id,String email,String name, boolean gender,Date birthday, String phone, Integer point){
        EUser blockEUser = new EUser(id,email,name,gender,birthday,phone,point);
        blockEUser.setBlock(new Block(name));

        eUserRepository.save(blockEUser);
    }

}