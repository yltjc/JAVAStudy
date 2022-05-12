package com.qdc.demoeurekacounsumerfeign.controller;

import com.qdc.demoeurekacounsumerfeign.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class UserController {

    @Autowired
    private UserClient userClient;
    @RequestMapping(value = "/hi")
    public String hello(){
        return userClient.hello();
    }
}
