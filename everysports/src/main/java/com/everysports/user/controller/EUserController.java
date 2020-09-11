package com.everysports.user.controller;

import com.everysports.user.domain.EUser;
import com.everysports.user.service.EUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EUserController {

    @Autowired
    private EUserService eUserService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody EUser resource){
        EUser eUser = eUserService.addEUser(resource);
        return eUser == resource
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
