package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(int id);
    User save(User user);
    void remove(int id);

}
