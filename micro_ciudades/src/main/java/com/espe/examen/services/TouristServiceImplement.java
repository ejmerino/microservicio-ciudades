package com.espe.examen.services;

import com.espe.examen.model.entity.Tourist;
import com.espe.examen.repositories.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristServiceImplement implements TouristService {

    @Autowired
    private TouristRepository touristRepository;

    @Override
    public List<Tourist> listAllTourists() {
        return touristRepository.findAll();
    }

    @Override
    public Tourist saveTourist(Tourist tourist) {
        return touristRepository.save(tourist);
    }
}
