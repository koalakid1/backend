package com.everysports.user.service;

import com.everysports.user.domain.EClassAttach;
import com.everysports.user.repository.EClassAttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EClassAttachService {

    @Autowired
    private EClassAttachRepository eClassAttachRepository;

    public EClassAttach addEClassAttach(EClassAttach eClassAttach){
        return eClassAttachRepository.save(eClassAttach);
    }

}
