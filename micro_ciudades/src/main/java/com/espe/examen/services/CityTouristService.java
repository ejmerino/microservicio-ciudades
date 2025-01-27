package com.espe.examen.services;

import com.espe.examen.model.entity.City;
import com.espe.examen.model.entity.CityTourist;
import com.espe.examen.repositories.CityTouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityTouristService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CityTouristRepository cityTouristRepository;

    private static final String TOURIST_SERVICE_URL = "http://localhost:8003/api/tourists"; // URL del microservicio de turistas

    public CityTourist addTouristToCity(Long cityId, Long touristId) {
        // Verificar si el turista existe en el microservicio de turistas
        String touristUrl = TOURIST_SERVICE_URL + "/" + touristId;
        ResponseEntity<Object> touristResponse = restTemplate.getForEntity(touristUrl, Object.class); // Response como Object

        if (!touristResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Turista no encontrado.");
        }

        // Si el turista existe, crear la relación en la tabla intermedia
        CityTourist cityTourist = new CityTourist();
        City city = new City(cityId);  // Crear la ciudad solo con el ID
        cityTourist.setCity(city);

        // Aquí solo necesitamos el ID del turista, ya que el microservicio de turistas nos da la información
        cityTourist.setTouristId(touristId); // Solo guardamos el ID, ya que Tourist está en otro microservicio

        return cityTouristRepository.save(cityTourist); // Guardamos la relación
    }
}
