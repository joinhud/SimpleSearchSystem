package com.joinhud.simplesearchsystem.service.impl;


import com.joinhud.simplesearchsystem.entity.Gain;
import com.joinhud.simplesearchsystem.repository.GainRepository;
import com.joinhud.simplesearchsystem.service.GainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
}
