package com.everysports.user.controller;

import com.everysports.user.domain.EClass;
import com.everysports.user.service.EClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
public class EClassController {

    @Autowired
    private EClassService eClassService;

    @PostMapping("/eclass")
    public ResponseEntity<?> create(@Valid @RequestBody EClass eClass) throws URISyntaxException {
        eClass = eClassService.addEClass(eClass);

        URI url = new URI("/eclass/" + eClass.getClass_ID());

        return ResponseEntity.created(url).body(eClass);
    }

}
