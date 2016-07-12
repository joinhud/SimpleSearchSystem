package com.joinhud.simplesearchsystem.controller;

import com.joinhud.simplesearchsystem.entity.Gain;
import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.service.ExpenseService;
import com.joinhud.simplesearchsystem.service.GainService;
import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private SimpleDateFormat htmlFormat = new SimpleDateFormat("yyyy-MM-dd");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        htmlFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(htmlFormat, true));
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String loginView(Model model) {

        return "index";
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String mainView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        return "main";
    }

    @RequestMapping(value = {"/gain_add"}, method = RequestMethod.GET)
    public String gainView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userName", name);

        Gain gain = new Gain();
        model.addAttribute("gainForm", gain);

        Date date = new Date();
        String dateStr = htmlFormat.format(date);
        model.addAttribute("currDate", dateStr);

        return "gain";
    }

    @RequestMapping(value = {"/gain_add"}, method = RequestMethod.POST)
    public String gainAdd(@ModelAttribute("gainForm") Gain gain, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByName(name);
        gain.setIdUser(user.getId());

        gainService.save(gain);
        loadCurrUser(model, name);

        return "main";
    }

    private void loadCurrUser(Model model, String userName) {

        if(!userName.equals("anonymousUser")) {
            User user = userService.getByName(userName);
            model.addAttribute("userName", userName);
            model.addAttribute("balance",
                    gainService.sumAllById(user.getId()) - expenseService.sumAllById(user.getId()));
            model.addAttribute("userGains", gainService.getByUserId(user.getId()));
            model.addAttribute("userExpenses", expenseService.getByUserId(user.getId()));
        }

    }

}
