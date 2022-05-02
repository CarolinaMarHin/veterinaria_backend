package com.ceiba.usuario.servicio.testdatabuilder.mascota;

import com.ceiba.mascota.comando.ComandoMascota;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoMascotaTestDataBuilder {

    private Long id;
    private String codigoMascota;
    private String nombreMascota;
    private String raza;
    private LocalDate fechaNacimientoMascota;
    private int peso;

    public ComandoMascotaTestDataBuilder() {
        codigoMascota = UUID.randomUUID().toString().substring(1, 10);
        nombreMascota = "Titan";
        raza = "Criollo";
        peso = 20;
        fechaNacimientoMascota = LocalDate.from(LocalDateTime.now());
    }

    public ComandoMascotaTestDataBuilder conNombre(String nombreMascota) {
        this.nombreMascota = nombreMascota;
        return this;
    }

    public ComandoMascota build() {
        return new ComandoMascota(id, codigoMascota, nombreMascota, raza, fechaNacimientoMascota, peso);
    }
}
