package com.espe.examen.services;

import com.espe.examen.model.entity.City;
import com.espe.examen.model.entity.CityTourist;
import com.espe.examen.repositories.CityRepository;
import com.espe.examen.repositories.CityTouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CityServiceImplement implements CityService {
    private static final String TOURIST_SERVICE_URL = "http://localhost:8003/api/tourists"; // URL del microservicio de turistas

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityTouristRepository cityTouristRepository; // Repositorio de la tabla intermedia

    @Autowired
    private RestTemplate restTemplate; // Para hacer la consulta al microservicio de turistas


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
}
