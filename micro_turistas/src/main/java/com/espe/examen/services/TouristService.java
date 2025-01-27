package com.espe.examen.services;

import com.espe.examen.model.entity.Tourist;

import java.util.List;

public interface TouristService {
    List<Tourist> findAllTourists();
    Tourist saveTourist(Tourist tourist);
    Tourist findTouristById(Long id);
    Tourist updateTourist(Long id, Tourist tourist);
    void deleteTouristById(Long id);
}
