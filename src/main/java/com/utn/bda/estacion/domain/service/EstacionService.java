package com.utn.bda.estacion.domain.service;

import com.utn.bda.estacion.domain.model.Estacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EstacionService {

    Optional<Estacion> findById(Integer id);

    Estacion create(
            String nombre,
            // no cambiar feche de creacion
            LocalDateTime fechaHoraCreacion,
            Float latitud,
            Float longitud
    );

    List<Estacion> findAll();

    void delete(Integer id);

    void update(
            Integer id,
            String nombre,
            LocalDateTime fechaHoraCreacion,
            Float latitud,
            Float longitud
    );

    Estacion consultarEstacionMasCercana(double latitud, double longitud);
}
