package com.espe.examen.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository {

    @Query("SELECT t.name FROM Tourist t WHERE t.id = :touristId")
    String findTouristNameById(@Param("id") Long touristId);
}
