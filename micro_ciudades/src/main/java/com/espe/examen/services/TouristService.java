package com.espe.examen.services;

import com.espe.examen.model.entity.Tourist;

import java.util.List;

public interface TouristService {
    List<Tourist> listAllTourists();

    Tourist saveTourist(Tourist tourist);
}
