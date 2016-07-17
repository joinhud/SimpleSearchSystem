package com.joinhud.simplesearchsystem.service;


import com.joinhud.simplesearchsystem.entity.Gain;

import java.util.Date;
import java.util.List;

public interface GainService {

    List<Gain> getAll();
    List<Gain> getByUserId(int id);
    Gain save(Gain gain);
    void delete(Gain gain);
    Gain deleteById(int id);
    Gain getGainById(int id);
    int sumAllById(int id);
    Gain edit(int id, Gain gain);
    List<Gain> getGainsByYear(int year, int uId);
    List<Gain> getGainsByMonth(int month, int uId);
    List<Gain> getGainsByDay(Date day, int uId);
    List<Gain> getGainsByValueRange(int min, int max, int uId);
    List<Gain> getGainsByCategory(String category, int uId);

}
