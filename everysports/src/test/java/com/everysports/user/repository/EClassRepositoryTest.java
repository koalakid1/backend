package com.everysports.user.repository;

import com.everysports.user.domain.EClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EClassRepositoryTest {

    @Autowired
    private EClassRepository eClassRepository;

    @Test
    void crud(){
        EClass eClass = new EClass();
    }

}