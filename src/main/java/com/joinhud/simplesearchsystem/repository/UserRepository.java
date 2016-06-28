package com.joinhud.simplesearchsystem.repository;


import com.joinhud.simplesearchsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
