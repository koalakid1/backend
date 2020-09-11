package com.everysports.user.controller;

import com.everysports.user.domain.EClass;
import com.everysports.user.domain.EClassAttach;
import com.everysports.user.service.EClassAttachService;
import com.everysports.user.service.EClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@RestController
public class EClassController {

    @Autowired
    private EClassService eClassService;

    @Autowired
    private EClassAttachService eClassAttachService;

    @GetMapping("/every/hotclass")
    public List<EClass> hotClassList(){
        return eClassService.getEClasses();
    }

    @PostMapping("/every/hotclass/get")
    public ResponseEntity<String> addHotClass(@RequestBody EClass resource){
        EClass eClass = eClassService.addEClass(resource);

        return eClass == resource
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/eclassattach")
    public ResponseEntity<String> addEClassAttach(@RequestBody EClassAttach resource){
        EClassAttach eClassAttach = eClassAttachService.addEClassAttach(resource);

        return eClassAttach == resource
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
