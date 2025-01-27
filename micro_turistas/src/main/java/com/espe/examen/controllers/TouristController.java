package com.espe.examen.controllers;

import com.espe.examen.model.entity.Tourist;
import com.espe.examen.services.TouristService;
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

    /**
     * Endpoint para listar todos los turistas.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTourists() {
        List<Tourist> tourists = touristService.findAllTourists();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turistas obtenidos exitosamente.");
        response.put("data", tourists);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener un turista por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTouristById(@PathVariable Long id) {
        Tourist tourist = touristService.findTouristById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turista obtenido exitosamente.");
        response.put("data", tourist);
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
     * Endpoint para actualizar un turista.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTourist(@PathVariable Long id, @RequestBody Tourist tourist) {
        Tourist updatedTourist = touristService.updateTourist(id, tourist);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turista actualizado exitosamente.");
        response.put("data", updatedTourist);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para eliminar un turista por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTourist(@PathVariable Long id) {
        touristService.deleteTouristById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Turista eliminado exitosamente.");
        return ResponseEntity.ok(response);
    }
}
