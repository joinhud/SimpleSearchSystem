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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
            Date day = new Date();
            String dayStr = htmlFormat.format(day);
            model.addAttribute("currDay", dayStr);
        }

    }

    private void loadCurrGainsExpenes(Model model, String userName) {
        if(!userName.equals("anonymousUser")) {
            User user = userService.getByName(userName);
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

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpView(Model model) {

        model.addAttribute("userForm", new User());

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpView(@ModelAttribute("userForm") User user, Model model) {

        userService.save(user);

        return "index";
    }

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String mainView(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);
        loadCurrGainsExpenes(model, name);

        return "main";
    }

    @RequestMapping(value = "/getByYear", method = RequestMethod.GET)
    public String getByYearView(Model model, HttpServletRequest request) {

        int year = Integer.parseInt(request.getParameter("year"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        model.addAttribute("userGains", gainService.getGainsByYear(year, getCurrUser().getId()));
        model.addAttribute("userExpenses", expenseService.getExpensesByYear(year, getCurrUser().getId()));

        return "main";
    }

    @RequestMapping(value = "/getByMonth", method = RequestMethod.GET)
    public String getByMonthView(Model model, HttpServletRequest request) {

        int month = Integer.parseInt(request.getParameter("month"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        model.addAttribute("userGains", gainService.getGainsByMonth(month, getCurrUser().getId()));
        model.addAttribute("userExpenses", expenseService.getExpensesByMonth(month, getCurrUser().getId()));

        return "main";
    }

    @RequestMapping(value = "/getByDay", method = RequestMethod.GET)
    public String getByDayView(Model model, HttpServletRequest request) throws ParseException {

        Date day = htmlFormat.parse(request.getParameter("day"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        model.addAttribute("userGains", gainService.getGainsByDay(day, getCurrUser().getId()));
        model.addAttribute("userExpenses", expenseService.getExpensesByDay(day, getCurrUser().getId()));

        return "main";
    }

    @RequestMapping(value = "/getByValueRange", method = RequestMethod.GET)
    public String getByValueRangeView(Model model, HttpServletRequest request) throws ParseException {

        int min = Integer.parseInt(request.getParameter("min_val"));
        int max = Integer.parseInt(request.getParameter("max_val"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        model.addAttribute("userGains", gainService.getGainsByValueRange(min, max, getCurrUser().getId()));
        model.addAttribute("userExpenses", expenseService.getExpensesByValueRange(min, max, getCurrUser().getId()));

        return "main";
    }

    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    public String getByCategoryView(Model model, HttpServletRequest request) throws ParseException {

        String gainCategory = request.getParameter("gainCategory");
        String expenseCategory = request.getParameter("expenseCategory");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        loadCurrUser(model, name);

        model.addAttribute("userGains", gainService.getGainsByCategory(gainCategory, getCurrUser().getId()));
        model.addAttribute(
                "userExpenses", expenseService.getExpensesByCategory(expenseCategory, getCurrUser().getId())
        );

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
        loadCurrGainsExpenes(model, user.getName());

        return "main";
    }

    @RequestMapping(value = {"/gain/gain/{id}"}, method = RequestMethod.POST)
    public String gainEdit(@PathVariable("id") int id,
                           @ModelAttribute("gainForm") Gain gain, Model model) {

        User user = getCurrUser();

        gainService.edit(id, gain);

        loadCurrUser(model, user.getName());
        loadCurrGainsExpenes(model, user.getName());

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
        loadCurrGainsExpenes(model, user.getName());

        return "main";
    }

    @RequestMapping(value = {"/expense/expense/{id}"}, method = RequestMethod.POST)
    public String expenseEdit(@PathVariable("id") int id,
                           @ModelAttribute("expenseForm") Expense expense, Model model) {

        User user = getCurrUser();

        expenseService.edit(id, expense);

        loadCurrUser(model, user.getName());
        loadCurrGainsExpenes(model, user.getName());

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
