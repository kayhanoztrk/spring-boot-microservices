package com.photoapp.api.account.PhotoAppApiAccountManagement.ui.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final Environment env;

    public AccountController(Environment environment){
        this.env = environment;
    }
    @GetMapping("/status/check")
    public String status(){
        return "working...!" + env.getProperty("local.server.port");
    }
}
