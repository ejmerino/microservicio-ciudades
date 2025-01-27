package com.espe.examen.controllers;

import com.espe.examen.model.entity.CityTourist;
import com.espe.examen.services.CityTouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/city-tourist")
public class CityTouristController {

    @Autowired
    private CityTouristService cityTouristService;

    /**
     * Endpoint para asignar un turista a una ciudad.
     */
    @PostMapping("/assign")
    public ResponseEntity<Map<String, Object>> assignTouristToCity(@RequestBody CityTourist cityTourist) {
        try {
            // Verifica si el cuerpo de la solicitud es correcto
            if (cityTourist.getCity() == null || cityTourist.getTourist() == null) {
                throw new RuntimeException("La ciudad y el turista son requeridos.");
            }

            CityTourist assigned = cityTouristService.assignTouristToCity(cityTourist);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Turista asignado exitosamente a la ciudad.");
            response.put("data", assigned);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Endpoint para listar todas las asignaciones.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAssignments() {
        List<Map<String, String>> assignments = cityTouristService.getAllAssignments();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Asignaciones obtenidas exitosamente.");
        response.put("data", assignments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<Map<String, Object>> getTouristsByCity(@PathVariable Long id) {
        try {
            List<CityTourist> cityTourists = cityTouristService.getTouristsByCity(id);

            // Si no se encuentran turistas para la ciudad
            if (cityTourists.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "No se encontraron turistas para esta ciudad.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Obtener el nombre de la ciudad una sola vez
            String cityName = cityTourists.get(0).getCity().getName();

            // Mapear la lista de CityTourist a nombres de turistas
            List<String> touristsNames = cityTourists.stream()
                    .map(cityTourist -> cityTourist.getTourist().getName()) // Solo el nombre del turista
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Turistas obtenidos exitosamente.");
            response.put("cityName", cityName);  // Nombre de la ciudad
            response.put("tourists", touristsNames);  // Lista de turistas

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error al obtener los turistas.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
