package com.everysports.user.service;

import com.everysports.user.domain.Block;
import com.everysports.user.domain.EUser;
import com.everysports.user.repository.BlockRepository;
import com.everysports.user.repository.EUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EUserService {

    @Autowired
    private EUserRepository eUserRepository;

    @Autowired
    private BlockRepository blockRepository;

    public EUser addEUser(EUser eUser) {
        return eUserRepository.save(eUser);
    }

    public List<EUser> getEUsers() {
        List<EUser> eUsers = eUserRepository.findAll();
        return eUsers.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EUser getEUser(Long id){
        EUser eUser = eUserRepository.findById(id).get();

        log.info("person : {}", eUser);

        return eUser;

    }
}
