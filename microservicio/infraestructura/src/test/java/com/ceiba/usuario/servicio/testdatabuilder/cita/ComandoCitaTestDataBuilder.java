package com.ceiba.usuario.servicio.testdatabuilder.cita;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoCitaTestDataBuilder {

    private Long id;
    private Long codigoMascota;
    private Long idVeterinario;
    private Long codigoServicio;
    private double totalPago;
    LocalDateTime fecha;

    public ComandoCitaTestDataBuilder() {
        codigoMascota = 1L;
        idVeterinario = 2L;
        codigoServicio = 1L;
        totalPago = 33000;
        fecha = LocalDateTime.now();
    }

    public ComandoCita build() {
        return new ComandoCita(id, codigoMascota, idVeterinario, codigoServicio, totalPago, fecha);
    }
}
