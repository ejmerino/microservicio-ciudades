package com.espe.examen.repositories;

import com.espe.examen.model.entity.CityTourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityTouristRepository extends JpaRepository<CityTourist, Long> {
    List<CityTourist> findByCityId(Long cityId);

    List<CityTourist> findByTouristId(Long touristId);

    Optional<CityTourist> findByCityIdAndTouristId(Long cityId, Long touristId);


}
