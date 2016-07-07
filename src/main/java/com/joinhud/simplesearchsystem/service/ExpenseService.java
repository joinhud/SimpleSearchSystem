package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAll();
    List<Expense> getByUserId(int id);
    Expense save(Expense gain);
    void delete(Expense gain);
    int sumAllById(int id);

}
