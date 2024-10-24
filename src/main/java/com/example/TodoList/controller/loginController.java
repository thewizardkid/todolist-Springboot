package com.example.TodoList.controller;

import com.example.TodoList.services.authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {

    private final authentication authentication;

    public loginController(com.example.TodoList.services.authentication authentication) {
        this.authentication = authentication;
    }

    //Return the login page when the user first opens the website
    @GetMapping(value = "login")
    public String gotoLoginPage(){
        return "login";
    }

    //Takes the username and password, does a harcoded check and puts the values in modelMap(to pass data between controller and the view layer)
    @PostMapping(value = "login")
    public String gotoWelcomePage(@RequestParam String name, String password, ModelMap model){
        if (authentication.authenticate(name, password)) {
            model.put("name",name);
            model.put("password",password);
            return "welcome";
        }
        //Returns login if the check is false
        else {
            return "login";
        }

    }
}
