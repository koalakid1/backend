package com.everysports.user.repository;

import com.everysports.user.domain.EUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EUserRepository extends JpaRepository<EUser, Long> {

    EUser save(EUser eUser);

}
