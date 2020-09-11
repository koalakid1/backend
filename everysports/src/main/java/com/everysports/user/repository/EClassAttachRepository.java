package com.everysports.user.repository;

import com.everysports.user.domain.EClass;
import com.everysports.user.domain.EClassAttach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EClassAttachRepository extends JpaRepository<EClassAttach, String> {
    EClassAttach save(EClassAttach eClassAttach);
}
