package com.joinhud.simplesearchsystem.service.impl;


import com.joinhud.simplesearchsystem.entity.Gain;
import com.joinhud.simplesearchsystem.repository.GainRepository;
import com.joinhud.simplesearchsystem.service.GainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GainServiceImpl implements GainService {

    @Autowired
    private GainRepository repository;

    public List<Gain> getAll() {
        return repository.findAll();
    }

    public Gain save(Gain gain) {
        return repository.saveAndFlush(gain);
    }

    public void delete(Gain gain) {
        repository.delete(gain);
    }

    public List<Gain> getByUserId(int id) {

        List<Gain> resultList = new ArrayList<Gain>();

        for(Gain temp : repository.findAll()) {
            if(temp.getIdUser() == id) {
                resultList.add(temp);
            }
        }

        if(resultList.isEmpty()) {
            return null;
        } else {
            return resultList;
        }
    }

    public int sumAllById(int id) {

        int sum = 0;

        for(Gain temp : repository.findAll()) {

            if(temp.getIdUser() == id) {
                sum += temp.getValue();
            }

        }

        return sum;

    }

    public Gain deleteById(int id) {

        Gain gain;

        for(Gain temp : repository.findAll()) {
            if(temp.getId() == id) {
                gain = temp;
                repository.delete(temp);
                return gain;
            }
        }

        return null;

    }

    public Gain getGainById(int id) {

        Gain gain = null;

        for(Gain temp : repository.findAll()) {
            if(temp.getId() == id) {
                gain = temp;
                break;
            }
        }

        return gain;
    }

    public Gain edit(int id, Gain gain) {

        for(Gain temp : repository.findAll()) {
            if(temp.getId() == id) {
                gain.setId(id);
                gain.setIdUser(temp.getIdUser());
                break;
            }
        }

        return repository.saveAndFlush(gain);
    }

    public List<Gain> getGainsByYear(int year, int uId) {

        List<Gain> result = new ArrayList<Gain>();
        Calendar calendar = Calendar.getInstance();

        for(Gain temp : repository.findAll()) {
            calendar.setTime(temp.getDate());
            if(calendar.get(Calendar.YEAR) == year) {
                result.add(temp);
            }
        }

        return result;
    }

    public List<Gain> getGainsByMonth(int month, int uId) {

        List<Gain> result = new ArrayList<Gain>();
        Calendar calendar = Calendar.getInstance();

        for(Gain temp : getByUserId(uId)) {
            calendar.setTime(temp.getDate());
            if((calendar.get(Calendar.MONTH) + 1) == month) {
                result.add(temp);
            }
        }

        return result;
    }

    public List<Gain> getGainsByDay(Date day, int uId) {

        List<Gain> result = new ArrayList<Gain>();

        for(Gain temp : getByUserId(uId)) {
            if(temp.getDate().equals(day)) {
                result.add(temp);
            }
        }

        return result;

    }

    public List<Gain> getGainsByValueRange(int min, int max, int uId) {

        List<Gain> result = new ArrayList<Gain>();

        if(max < min) {
            return null;
        }
        if(max == min) {
            for(Gain temp : getByUserId(uId)) {
                if(temp.getValue() == min) {
                    result.add(temp);
                }
            }
        } else {
            for(Gain temp : getByUserId(uId)) {
                if(temp.getValue() >= min && temp.getValue() <= max) {
                    result.add(temp);
                }
            }
        }

        return result;
    }

    public List<Gain> getGainsByCategory(String category, int uId) {

        List<Gain> result = new ArrayList<Gain>();

        for(Gain temp : getByUserId(uId)) {
            if(temp.getCategory().equals(category)) {
                result.add(temp);
            }
        }

        return result;
    }
}
