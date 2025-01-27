package com.espe.examen.services;

import com.espe.examen.model.entity.CityTourist;

import java.util.List;
import java.util.Map;

public interface CityTouristService {
    CityTourist assignTouristToCity(CityTourist cityTourist);

    List<Map<String, String>> getAllAssignments();


    List<Object> listCitiesByTourist(Long touristId);

    List<CityTourist> getTouristsByCity(Long cityId);

}
