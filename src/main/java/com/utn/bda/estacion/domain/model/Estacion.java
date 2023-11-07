package com.utn.bda.estacion.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "estaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estacion {
    @Id
    private Integer id;

    private String nombre;

    private LocalDateTime fechaHoraCreacion;

    private Float latitud;

    private Float longitud;
}
