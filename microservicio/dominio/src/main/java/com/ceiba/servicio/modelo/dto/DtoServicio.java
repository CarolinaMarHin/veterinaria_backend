package com.ceiba.servicio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoServicio {

    private Long id;
    private String nombreServicio;
    private double precioServicio;

}
