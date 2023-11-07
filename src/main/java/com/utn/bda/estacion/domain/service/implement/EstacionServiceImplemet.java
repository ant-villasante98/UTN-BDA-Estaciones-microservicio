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

    @Override
    public Estacion consultarEstacionMasCercana(double latitud, double longitud) {
        List<Estacion> estaciones = estacionRepository.findAll(); // Obtén todas las estaciones

        Estacion estacionMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }

        return estacionMasCercana;
    }

    private double calcularDistancia(double latitud1, double longitud1, double latitud2, double longitud2) {
// Aquí debes implementar el cálculo de la distancia entre dos coordenadas geográficas.
// Puedes utilizar la fórmula de la distancia euclidiana para calcular la distancia.
// Ten en cuenta que esta fórmula es una simplificación y no es 100% precisa, pero es suficiente para este ejemplo.
        double latitudDiferencia = latitud1 - latitud2;
        double longitudDiferencia = longitud1 - longitud2;
        return Math.sqrt(latitudDiferencia * latitudDiferencia + longitudDiferencia * longitudDiferencia);
    }
}
