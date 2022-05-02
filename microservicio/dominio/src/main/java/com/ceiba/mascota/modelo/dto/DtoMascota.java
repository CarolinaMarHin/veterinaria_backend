package com.ceiba.mascota.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoMascota {

    private Long id;
    private String codigoMascota;
    private String nombreMascota;
    private String raza;
    private LocalDate fechaNacimientoMascota;
    private int peso;

}
