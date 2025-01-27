package com.espe.examen.services;

import com.espe.examen.model.entity.City;
import com.espe.examen.model.entity.CityTourist;
import com.espe.examen.model.entity.Tourist;
import com.espe.examen.repositories.CityRepository;
import com.espe.examen.repositories.CityTouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImplement implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityTouristRepository cityTouristRepository;

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + id));
    }

    @Override
    public void deleteCityById(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new RuntimeException("City not found with ID: " + id);
        }
        cityRepository.deleteById(id);
    }

    @Override
    public List<Tourist> findTouristsByCityId(Long cityId) {
        return cityTouristRepository.findByCityId(cityId).stream()
                .map(CityTourist::getTourist)
                .collect(Collectors.toList());
    }
}
