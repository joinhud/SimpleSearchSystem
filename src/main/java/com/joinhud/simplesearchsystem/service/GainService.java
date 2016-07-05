package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.Gain;

import java.util.List;

public interface GainService {

    List<Gain> getAll();
    List<Gain> getByUserId(int id);
    Gain save(Gain gain);
    void delete(Gain gain);

}
