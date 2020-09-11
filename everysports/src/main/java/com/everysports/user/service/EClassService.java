package com.everysports.user.service;

import com.everysports.user.domain.EClass;
import com.everysports.user.repository.EClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EClassService {

    private EClassRepository eClassRepository;

    public EClassService(EClassRepository eClassRepository) {
        this.eClassRepository = eClassRepository;
    }


    public EClass addEClass(EClass eClass){
        return eClassRepository.save(eClass);
    }

    public List<EClass> getEClasses(){ return eClassRepository.findAll(); }

}
