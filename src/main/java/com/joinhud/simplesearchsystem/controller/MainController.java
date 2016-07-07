package com.joinhud.simplesearchsystem.controller;

import com.joinhud.simplesearchsystem.entity.Gain;
import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.service.ExpenseService;
import com.joinhud.simplesearchsystem.service.GainService;
import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GainService gainService;

    @Autowired
    private ExpenseService expenseService;

    private User currUser;

    private SimpleDateFormat htmlFormat = new SimpleDateFormat("MM/dd/yyyy");

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String loginView(Model model) {

        return "index";
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String mainView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(!name.equals("anonymousUser")) {
            User user = userService.getByName(name);
            model.addAttribute("userName", name);
            model.addAttribute("balance",
                    gainService.sumAllById(user.getId()) - expenseService.sumAllById(user.getId()));
            model.addAttribute("userGains", gainService.getByUserId(user.getId()));
            model.addAttribute("userExpenses", expenseService.getByUserId(user.getId()));
        }

        return "main";
    }

    @RequestMapping(value = {"/gain_add"}, method = RequestMethod.GET)
    public String gainView(Model model) {

        Gain gain = new Gain();
        model.addAttribute("gainForm", gain);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userName", name);

        Date date = new Date();
        String dateStr = htmlFormat.format(date);
        model.addAttribute("currDate", dateStr);

        return "gain";
    }

}
