package com.moneytracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hpishepei on 11/27/16.
 */

@RestController
public class WelcomeController {
    @RequestMapping("/")
    public String index(){
        return "Welcome! Thanks for using MoneyTracker RESTful web services!";
    }
}
