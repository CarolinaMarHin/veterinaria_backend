package com.ceiba.usuario.servicio.testdatabuilder.cita;

import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDateTime;
import java.util.Random;

public class CitaTestDataBuilder {

    private Long id;
    private Long codigoMascota;
    private Long idVeterinario;
    private Long codigoServicio;
    private double totalPago;
    LocalDateTime fecha;


    public CitaTestDataBuilder() {
        codigoMascota = 1L;
        idVeterinario = 1L;
        codigoServicio = 1L;
        totalPago = 200000;
        fecha = LocalDateTime.now();
    }

    public CitaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public CitaTestDataBuilder conCodigoMascota(Long codigoMascota) {
        this.codigoMascota = codigoMascota;
        return this;
    }

    public CitaTestDataBuilder conIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
        return this;
    }

    public CitaTestDataBuilder conCodigoServicio(Long codigoServicio) {
        this.codigoServicio = codigoServicio;
        return this;
    }

    public CitaTestDataBuilder conFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conFinDeSemana(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public Cita build() {
        return new Cita(id, codigoMascota, idVeterinario, codigoServicio, totalPago, fecha);
    }
}
