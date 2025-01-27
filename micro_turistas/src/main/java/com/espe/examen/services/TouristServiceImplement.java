package com.espe.examen.services;

import com.espe.examen.model.entity.Tourist;
import com.espe.examen.repositories.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TouristServiceImplement implements TouristService {
    @Autowired
    private RestTemplate restTemplate;

    private final String CITY_SERVICE_URL = "http://localhost:8002/api/cities"; // Dirección del microservicio de ciudades

    // Método para agregar un turista a una ciudad
    public void addTouristToCity(Long cityId, Long touristId) {
        String url = CITY_SERVICE_URL + "/" + cityId + "/tourists/" + touristId;

        HttpEntity<Void> entity = new HttpEntity<>(null);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Turista agregado a la ciudad.");
        } else {
            System.out.println("Error al agregar turista a la ciudad.");
        }
    }

    @Autowired
    private TouristRepository touristRepository;

    @Override
    public List<Tourist> findAllTourists() {
        return touristRepository.findAll();
    }

    @Override
    public Tourist saveTourist(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    @Override
    public Tourist findTouristById(Long id) {
        return touristRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tourist not found with ID: " + id));
    }

    @Override
    public Tourist updateTourist(Long id, Tourist tourist) {
        Tourist existingTourist = touristRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tourist not found with ID: " + id));
        existingTourist.setName(tourist.getName());
        existingTourist.setEmail(tourist.getEmail());
        existingTourist.setPhone(tourist.getPhone());
        return touristRepository.save(existingTourist);
    }

    @Override
    public void deleteTouristById(Long id) {
        if (!touristRepository.existsById(id)) {
            throw new RuntimeException("Tourist not found with ID: " + id);
        }
        touristRepository.deleteById(id);
    }
}
