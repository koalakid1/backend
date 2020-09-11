package com.everysports.user.repository;

import com.everysports.user.domain.EUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EUserRepository extends JpaRepository<EUser, Long> {

    EUser save(EUser eUser);

    List<EUser> findAll();

}
