package com.joinhud.simplesearchsystem.controller;

import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.service.GainService;
import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GainService gainService;

    private User currUser;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String loginView(Model model) {
        return "index";
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String mainView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByName(name);
        model.addAttribute("userName", name);
        model.addAttribute("userGains", gainService.getByUserId(user.getId()));

        return "main";
    }

}
