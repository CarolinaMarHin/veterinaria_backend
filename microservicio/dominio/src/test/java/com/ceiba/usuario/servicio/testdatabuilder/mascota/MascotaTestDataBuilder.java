package com.ceiba.usuario.servicio.testdatabuilder.mascota;

import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class MascotaTestDataBuilder {

    private Long id;
    private String codigoMascota;
    private String nombreMascota;
    private String raza;
    private LocalDate fechaNacimientoMascota;
    private int peso;

    public MascotaTestDataBuilder() {
        codigoMascota = "4d0dd56-6";
        nombreMascota = "Titan";
        raza = "Criollo";
        peso = 20;
        fechaNacimientoMascota = LocalDate.from(LocalDateTime.now());
    }

    public MascotaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MascotaTestDataBuilder conCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
        return this;
    }

    public MascotaTestDataBuilder conFechaNacimientoMascota(LocalDate fechaNacimientoMascota) {
        this.fechaNacimientoMascota = fechaNacimientoMascota;
        return this;
    }

    public MascotaTestDataBuilder conRaza(String raza) {
        this.raza = raza;
        return this;
    }

    public MascotaTestDataBuilder conNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
        return this;
    }

    public MascotaTestDataBuilder conPeso(int peso) {
        this.peso = peso;
        return this;
    }

    public Mascota build() {
        return new Mascota(id, codigoMascota, nombreMascota, raza, fechaNacimientoMascota, peso);
    }
}
