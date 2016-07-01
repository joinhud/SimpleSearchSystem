package com.joinhud.simplesearchsystem.controller;

import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String loginPage() {

        return "index";
    }

}
