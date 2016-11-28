package com.moneytracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hpishepei on 11/27/16.
 */

@Controller
@RequestMapping("/moneytracker")
public class WelcomeController {


    @RequestMapping("/transaction")
    public String getIndexPage(){
        return "index";
    }
}
