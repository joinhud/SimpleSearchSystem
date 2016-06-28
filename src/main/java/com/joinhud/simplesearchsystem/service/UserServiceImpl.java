package com.joinhud.simplesearchsystem.service;

import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(int id) {
        return repository.findOne(id);
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    public void remove(int id) {
        repository.delete(id);
    }

}
