package com.joinhud.simplesearchsystem.service.impl;

import com.joinhud.simplesearchsystem.entity.Expense;
import com.joinhud.simplesearchsystem.repository.ExpenseRepository;
import com.joinhud.simplesearchsystem.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public List<Expense> getAll() {
        return repository.findAll();
    }

    public List<Expense> getByUserId(int id) {
        List<Expense> resultList = new ArrayList<Expense>();

        for(Expense temp : repository.findAll()) {
            if(temp.getIdUser() == id) {
                resultList.add(temp);
            }
        }

        if(resultList.isEmpty()) {
            return null;
        } else {
            return resultList;
        }
    }

    public Expense save(Expense expense) {
        return repository.saveAndFlush(expense);
    }

    public void delete(Expense expense) {
        repository.delete(expense);
    }

    public int sumAllById(int id) {

        int sum = 0;

        for(Expense temp : repository.findAll()) {

            if(temp.getIdUser() == id) {
                sum += temp.getValue();
            }

        }

        return sum;

    }

    public Expense deleteById(int id) {

        Expense expense;

        for(Expense temp : repository.findAll()) {
            if(temp.getId() == id) {
                expense = temp;
                repository.delete(temp);
                return expense;
            }
        }

        return null;
    }

    public Expense getExpenseById(int id) {
        Expense expense = null;

        for(Expense temp : repository.findAll()) {
            if(temp.getId() == id) {
                expense = temp;
                break;
            }
        }

        return expense;
    }

    public Expense edit(int id, Expense expense) {
        for(Expense temp : repository.findAll()) {
            if(temp.getId() == id) {
                expense.setId(id);
                expense.setIdUser(temp.getIdUser());
                break;
            }
        }

        return repository.saveAndFlush(expense);
    }

    public List<Expense> getExpensesByYear(int year, int uId) {

        List<Expense> result = new ArrayList<Expense>();
        Calendar calendar = Calendar.getInstance();

        for(Expense temp : getByUserId(uId)) {
            calendar.setTime(temp.getDate());
            if(calendar.get(Calendar.YEAR) == year) {
                result.add(temp);
            }
        }

        return result;
    }

    public List<Expense> getExpensesByMonth(int month, int uId) {
        List<Expense> result = new ArrayList<Expense>();
        Calendar calendar = Calendar.getInstance();

        for(Expense temp : getByUserId(uId)) {
            calendar.setTime(temp.getDate());
            if((calendar.get(Calendar.MONTH) + 1) == month) {
                result.add(temp);
            }
        }

        return result;
    }

    public List<Expense> getExpensesByDay(Date day, int uId) {
        List<Expense> result = new ArrayList<Expense>();

        for(Expense temp : getByUserId(uId)) {
            if(temp.getDate().equals(day)) {
                result.add(temp);
            }
        }

        return result;
    }

    public List<Expense> getExpensesByValueRange(int min, int max, int uId) {

        List<Expense> result = new ArrayList<Expense>();

        if(max < min) {
            return null;
        }
        if(max == min) {
            for(Expense temp : getByUserId(uId)) {
                if(temp.getValue() == min) {
                    result.add(temp);
                }
            }
        } else {
            for(Expense temp : getByUserId(uId)) {
                if(temp.getValue() >= min && temp.getValue() <= max) {
                    result.add(temp);
                }
            }
        }

        return result;
    }

    public List<Expense> getExpensesByCategory(String category, int uId) {

        List<Expense> result = new ArrayList<Expense>();

        for(Expense temp : getByUserId(uId)) {
            if(temp.getCategory().equals(category)) {
                result.add(temp);
            }
        }

        return result;
    }
}
