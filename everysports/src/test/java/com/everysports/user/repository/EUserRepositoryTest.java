package com.everysports.user.repository;

import com.everysports.user.domain.EUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EUserRepositoryTest {

    @Autowired
    private EUserRepository eUserRepository;

    @Test
    void crud(){
        EUser eUser = new EUser(1L,"koalakid154@gmail.com","koalakid154",true,new Date(),"010-4110-6893",500);

        eUserRepository.save(eUser);

        List<EUser> userList = eUserRepository.findAll();

        assertThat(userList.size()).isEqualTo(1);
        assertThat(userList.get(0).getUser_Name()).isEqualTo("koalakid154");
    }

    @Test
    void hashCodeAndEquals(){
        EUser eUser1 = new EUser(1L,"koalakid154@gmail.com","koalakid154",true,new Date(),"010-4110-6893",500);
        EUser eUser2 = new EUser(1L,"koalakid154@gmail.com","koalakid154",true,new Date(),"010-4110-6893",500);


        System.out.println(eUser1.hashCode());
        System.out.println(eUser2.hashCode());
    }

}