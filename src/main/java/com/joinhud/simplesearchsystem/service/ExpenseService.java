package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    List<Expense> getAll();
    List<Expense> getByUserId(int id);
    Expense save(Expense gain);
    void delete(Expense gain);
    int sumAllById(int id);
    Expense getExpenseById(int id);
    Expense deleteById(int id);
    Expense edit(int id, Expense expense);
    List<Expense> getExpensesByYear(int year, int uId);
    List<Expense> getExpensesByMonth(int month, int uId);
    List<Expense> getExpensesByDay(Date day, int uId);
    List<Expense> getExpensesByValueRange(int min, int max, int uId);
    List<Expense> getExpensesByCategory(String category, int uId);

}
