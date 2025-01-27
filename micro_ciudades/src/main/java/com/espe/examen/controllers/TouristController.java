package com.espe.examen.controllers;

import com.espe.examen.model.entity.Tourist;
import com.espe.examen.services.TouristService;
import com.espe.examen.services.CityTouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    @Autowired
    private TouristService touristService;

    @Autowired
    private CityTouristService cityTouristService;

    /**
     * Endpoint para listar todos los turistas.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTourists() {
        List<Tourist> tourists = touristService.listAllTourists();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turistas obtenidos exitosamente.");
        response.put("data", tourists);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para crear un nuevo turista.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTourist(@RequestBody Tourist tourist) {
        Tourist savedTourist = touristService.saveTourist(tourist);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turista creado exitosamente.");
        response.put("data", savedTourist);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para obtener todas las ciudades asociadas a un turista.
     */
    @GetMapping("/{touristId}/cities")
    public ResponseEntity<Map<String, Object>> getCitiesByTourist(@PathVariable Long touristId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Object> cities = cityTouristService.listCitiesByTourist(touristId);
            response.put("message", "Ciudades asociadas al turista obtenidas exitosamente.");
            response.put("data", cities);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
