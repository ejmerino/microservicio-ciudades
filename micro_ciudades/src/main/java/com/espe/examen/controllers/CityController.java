package com.espe.examen.controllers;

import com.espe.examen.model.entity.City;
import com.espe.examen.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * Endpoint para listar todas las ciudades.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCities() {
        List<City> cities = cityService.findAllCities();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ciudades obtenidas exitosamente.");
        response.put("data", cities);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para crear una nueva ciudad.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCity(@RequestBody City city) {
        City savedCity = cityService.saveCity(city);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Ciudad creada exitosamente.");
        response.put("data", savedCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
