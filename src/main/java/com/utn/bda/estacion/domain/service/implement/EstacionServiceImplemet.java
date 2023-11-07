package com.utn.bda.estacion.domain.service.implement;

import com.utn.bda.estacion.domain.model.Estacion;
import com.utn.bda.estacion.domain.repository.EstacionRepository;
import com.utn.bda.estacion.domain.service.EstacionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionServiceImplemet implements EstacionService {

    private final EstacionRepository estacionRepository;

    public EstacionServiceImplemet(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    @Override
    public Optional<Estacion> findById(Integer id) {
        return this.estacionRepository.findById(id);
    }

    @Override
    public Estacion create(String nombre, LocalDateTime fechaHoraCreacion, Float latitud, Float longitud) {
        Integer maxEstacionId = this.estacionRepository.getMaxEstacioId();
        if (maxEstacionId == null){
            maxEstacionId = 0;
        }
        Integer estacionId = maxEstacionId +1;
        return this.estacionRepository.save(new Estacion(maxEstacionId,nombre, fechaHoraCreacion,latitud,longitud));
    }

    @Override
    public List<Estacion> findAll() {
        return this.estacionRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        Optional<Estacion> estacion = this.estacionRepository.findById(id);
        this.estacionRepository.delete(estacion.orElseThrow());
    }

    @Override
    public void update(Integer id, String nombre, LocalDateTime fechaHoraCreacion, Float latitud, Float longitud) {
        Estacion estacion = this.findById(id).orElseThrow();
        estacion.setNombre(nombre);
        estacion.setLatitud(latitud);
        estacion.setLongitud(longitud);
        this.estacionRepository.save(estacion);
    }
}
