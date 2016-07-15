package com.joinhud.simplesearchsystem.controller;

import com.joinhud.simplesearchsystem.entity.Expense;
import com.joinhud.simplesearchsystem.entity.Gain;
import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.service.ExpenseService;
import com.joinhud.simplesearchsystem.service.GainService;
import com.joinhud.simplesearchsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    private User getCurrUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return userService.getByName(name);
    }

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

    @RequestMapping(value = {"/gain"}, method = RequestMethod.GET)
    public String gainAddView(Model model) {

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

    @RequestMapping(value = {"/gain/{id}"}, method = RequestMethod.GET)
    public String gainEditView(@PathVariable("id") int id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userName", name);

        Gain gain = gainService.getGainById(id);
        model.addAttribute("gainForm", gain);

        Date date = gain.getDate();
        String dateStr = htmlFormat.format(date);
        model.addAttribute("currDate", dateStr);

        return "gain";
    }

    @RequestMapping(value = {"/gain/"}, method = RequestMethod.POST)
    public String gainAdd(@ModelAttribute("gainForm") Gain gain, Model model) {

        User user = getCurrUser();
        gain.setIdUser(user.getId());

        gainService.save(gain);
        loadCurrUser(model, user.getName());

        return "main";
    }

    @RequestMapping(value = {"/gain/gain/{id}"}, method = RequestMethod.POST)
    public String gainEdit(@PathVariable("id") int id,
                           @ModelAttribute("gainForm") Gain gain, Model model) {

        User user = getCurrUser();

        gainService.edit(id, gain);

        loadCurrUser(model, user.getName());

        return "main";
    }

    @RequestMapping(value = {"/expense/{id}"}, method = RequestMethod.GET)
    public String expenseView(@PathVariable("id") int id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userName", name);

        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expenseForm", expense);

        Date date = expense.getDate();
        String dateStr = htmlFormat.format(date);
        model.addAttribute("currDate", dateStr);

        return "expense";
    }

    @RequestMapping(value = {"/expense"}, method = RequestMethod.GET)
    public String expenseEditView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userName", name);

        Expense expense = new Expense();
        model.addAttribute("expenseForm", expense);

        Date date = new Date();
        String dateStr = htmlFormat.format(date);
        model.addAttribute("currDate", dateStr);

        return "expense";
    }

    @RequestMapping(value = {"/expense/"}, method = RequestMethod.POST)
    public String expenseAdd(@ModelAttribute("expenseForm") Expense expense, Model model) {

        User user = getCurrUser();
        expense.setIdUser(user.getId());

        expenseService.save(expense);

        loadCurrUser(model, user.getName());

        return "main";
    }

    @RequestMapping(value = {"/expense/expense/{id}"}, method = RequestMethod.POST)
    public String expenseEdit(@PathVariable("id") int id,
                           @ModelAttribute("expenseForm") Expense expense, Model model) {

        User user = getCurrUser();

        expenseService.edit(id, expense);

        loadCurrUser(model, user.getName());

        return "main";
    }

    @RequestMapping(value = "/deleteGain/{id}", method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteGain(@PathVariable("id") int id) {
        gainService.deleteById(id);
    }

    @RequestMapping(value = "/deleteExpense/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteExpense(@PathVariable("id") int id) {
        expenseService.deleteById(id);
    }

}
