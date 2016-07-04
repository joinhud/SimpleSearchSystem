package com.joinhud.simplesearchsystem.service.impl;

import com.joinhud.simplesearchsystem.entity.User;
import com.joinhud.simplesearchsystem.repository.UserRepository;
import com.joinhud.simplesearchsystem.service.UserService;
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

    public User getByName(String name) {
        List<User> users = repository.findAll();

        for(User temp : users) {
            if(temp.getName().equals(name)) {
                return temp;
            }
        }

        return null;
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
