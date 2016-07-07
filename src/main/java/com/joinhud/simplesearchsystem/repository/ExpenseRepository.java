package com.joinhud.simplesearchsystem.repository;


import com.joinhud.simplesearchsystem.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
