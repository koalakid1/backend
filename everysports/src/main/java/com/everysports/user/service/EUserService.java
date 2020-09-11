package com.everysports.user.service;

import com.everysports.user.domain.EUser;
import com.everysports.user.repository.EUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EUserService {

    private EUserRepository eUserRepository;

    public EUserService(EUserRepository eUserRepository) {
        this.eUserRepository = eUserRepository;
    }

    public EUser addEUser(EUser eUser) {
        return eUserRepository.save(eUser);
    }

    public List<EUser> getEUsers() {
        List<EUser> eUsers = eUserRepository.findAll();
        return eUsers;
    }
}
