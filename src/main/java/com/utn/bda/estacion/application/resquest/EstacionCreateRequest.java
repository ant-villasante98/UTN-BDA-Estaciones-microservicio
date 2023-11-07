package com.utn.bda.estacion.application.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionCreateRequest {
    private String nombre;
    private LocalDateTime fechaHoraCreacion;

    private Float latitud;

    private Float longitud;

    public LocalDateTime getFechaHoraCreacion(){
        return LocalDateTime.now();
    }

}
