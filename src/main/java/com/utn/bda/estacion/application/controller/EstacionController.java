package com.utn.bda.estacion.application.controller;

import com.utn.bda.estacion.application.resquest.EstacionCreateRequest;
import com.utn.bda.estacion.domain.model.Estacion;
import com.utn.bda.estacion.domain.service.EstacionService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estaciones")
public class EstacionController {

    private final EstacionService estacionService;

    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Estacion> estacionList = this.estacionService.findAll();

        return new ResponseEntity<>(estacionList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){
        try {
            Estacion estacion = this.estacionService.findById(id).orElseThrow();
            return new ResponseEntity<>(estacion,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("no se pudo encontrar",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/estacion-mas-cercana")
    public Estacion consultarEstacionMasCercana(
            @RequestParam("latitud") double latitud,
            @RequestParam("longitud") double longitud
    ) {
        return estacionService.consultarEstacionMasCercana(latitud, longitud);
    }

    @PostMapping
    public ResponseEntity<Estacion> create(@RequestBody EstacionCreateRequest estacionCreate){
        try{
            Estacion estacion = this.estacionService.create(
                    estacionCreate.getNombre(),
                    estacionCreate.getFechaHoraCreacion(),
                    estacionCreate.getLatitud(),
                    estacionCreate.getLongitud()
            );
            return new ResponseEntity<>(estacion,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace() ;
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
