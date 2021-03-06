package com.ceiba.usuario.servicio.testdatabuilder.cita;

import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CitaTestDataBuilder {

    private Long id;
    private Long codigoMascota;
    private Long idVeterinario;
    private Long codigoServicio;
    private double totalPago;
    LocalDate fecha;

    public CitaTestDataBuilder() {
        codigoMascota = 1L;
        idVeterinario = 1L;
        codigoServicio = 1L;
        totalPago = 200000;
        fecha = LocalDate.of(2022, 05, 12);
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

    public CitaTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conFinDeSemana(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public Cita build() {
        return new Cita(id, codigoMascota, idVeterinario, codigoServicio, totalPago, fecha);
    }
}
