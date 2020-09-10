package com.everysports.user.controller;

import com.everysports.user.domain.EUser;
import com.everysports.user.service.EUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EUserController {

    @Autowired
    private EUserService eUserService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody EUser eUser) throws URISyntaxException {
        eUser = eUserService.addEUser(eUser);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
