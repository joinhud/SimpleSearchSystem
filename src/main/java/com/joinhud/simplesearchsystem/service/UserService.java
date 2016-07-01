package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(int id);
    //User getByName(String name);
    User save(User user);
    void delete(int id);

}
