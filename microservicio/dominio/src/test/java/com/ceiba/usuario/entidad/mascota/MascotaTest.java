package com.ceiba.usuario.entidad.mascota;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.usuario.servicio.testdatabuilder.mascota.MascotaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MascotaTest {

    @Test
    @DisplayName("Deberia crear correctamente la mascota")
    void deberiaCrearCorrectamenteLaMascota() {
        LocalDate fechaNacimientoMascota = LocalDate.from(LocalDateTime.now());
        Mascota mascota = new MascotaTestDataBuilder().conFechaNacimientoMascota(fechaNacimientoMascota).conId(1L).build();
        assertEquals(1, mascota.getId());
        assertEquals("Titan", mascota.getNombreMascota());
        assertEquals("4d0dd56-6", mascota.getCodigoMascota());
        assertEquals(fechaNacimientoMascota, mascota.getFechaNacimientoMascota());
        assertEquals("Criollo", mascota.getRaza());
        assertEquals(20, mascota.getPeso());
    }

    @Test
    void deberiaFallarSinNombreDeMascota() {
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder().conNombreMascota(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    mascotaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de la mascota");
    }

    @Test
    void deberiaFallarSinFechaNaciemiento() {
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder().conFechaNacimientoMascota(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    mascotaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de nacimiento");
    }

    @Test
    void deberiaFallarSinCodigoMascota() {
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder().conCodigoMascota(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    mascotaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de la mascota");
    }


    @Test
    void deberiaFallarSinPeso() {
        MascotaTestDataBuilder mascotaTestDataBuilder = new MascotaTestDataBuilder().conPeso(0).conId(1L);
        BasePrueba.assertThrows(() -> {
                    mascotaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Se debe ingresar el peso de la mascota");
    }
}
