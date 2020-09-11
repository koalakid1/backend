package com.everysports.user.repository;

import com.everysports.user.domain.EClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EClassRepository extends JpaRepository<EClass, Long> {

    EClass save(EClass eClass);

    List<EClass> findAll();

}
