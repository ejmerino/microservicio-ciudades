package com.espe.examen.services;

import com.espe.examen.model.entity.CityTourist;
import com.espe.examen.repositories.CityTouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CityTouristServiceImplement implements CityTouristService {

    @Autowired
    private CityTouristRepository cityTouristRepository;

    @Override
    public CityTourist assignTouristToCity(CityTourist cityTourist) {
        if (cityTouristRepository.findByCityIdAndTouristId(cityTourist.getCity().getId(), cityTourist.getTourist().getId()).isPresent()) {
            throw new RuntimeException("El turista ya está asignado a esta ciudad.");
        }
        return cityTouristRepository.save(cityTourist);
    }

    @Override
    public List<Map<String, String>> getAllAssignments() {
        return cityTouristRepository.findAll().stream()
                .map(cityTourist -> {
                    Map<String, String> assignment = new HashMap<>();
                    assignment.put("cityName", cityTourist.getCity().getName()); // Nombre de la ciudad
                    assignment.put("touristName", cityTourist.getTourist().getName()); // Nombre del turista
                    return assignment;
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<Object> listCitiesByTourist(Long touristId) {
        return cityTouristRepository.findByTouristId(touristId)
                .stream()
                .map(cityTourist -> cityTourist.getCity()) // Mapea solo la ciudad asociada
                .collect(Collectors.toList());
    }

    @Override
    public List<CityTourist> getTouristsByCity(Long cityId) {
        return cityTouristRepository.findByCityId(cityId); // Llamada directa al repositorio
    }
}
