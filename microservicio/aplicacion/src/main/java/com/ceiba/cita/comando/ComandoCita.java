package com.ceiba.cita.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita {

    private Long id;
    private Long codigoMascota;
    private Long idVeterinario;
    private Long codigoServicio;
    private double totalPago;
    LocalDate fecha;
}