package com.espe.examen.services;

import com.espe.examen.model.entity.City;
import com.espe.examen.model.entity.Tourist;

import java.util.List;

public interface CityService {
    List<City> findAllCities();

    City saveCity(City city);

    City findCityById(Long id);

    void deleteCityById(Long id);

    List<Tourist> findTouristsByCityId(Long cityId);
}
