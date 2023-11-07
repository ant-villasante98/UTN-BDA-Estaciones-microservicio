package com.utn.bda.estacion.domain.repository;

import com.utn.bda.estacion.domain.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstacionRepository extends JpaRepository<Estacion,Integer> {

    @Query(value = "SELECT MAX(e.ID)  FROM ESTACIONES e", nativeQuery = true)
    Integer getMaxEstacioId();
}
