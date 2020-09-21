package com.everysports.user.controller;

import com.everysports.user.domain.EUser;
import com.everysports.user.service.EUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @GetMapping("euser/{user_ID}")
    public EUser getEUser(@PathVariable("user_ID")Long user_ID){
        return eUserService.getEUser(user_ID);
    }
}
