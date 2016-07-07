package com.joinhud.simplesearchsystem.service.impl;

import com.joinhud.simplesearchsystem.entity.Expense;
import com.joinhud.simplesearchsystem.repository.ExpenseRepository;
import com.joinhud.simplesearchsystem.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
