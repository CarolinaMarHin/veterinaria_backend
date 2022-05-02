package com.ceiba.usuario.entidad.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.servicio.testdatabuilder.cita.CitaTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.mascota.MascotaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitaTest {

    @Test
    @DisplayName("Deberia crear correctamente la cita")
    void deberiaCrearCorrectamenteLaCita() {
        // arrange
        LocalDateTime fechaCita = LocalDateTime.now();
        //act
        Cita cita = new CitaTestDataBuilder().conFecha(fechaCita).conId(1L).build();
        //assert
        assertEquals(1, cita.getId());
        assertEquals(1, cita.getCodigoMascota());
        assertEquals(1, cita.getIdVeterinario());
        assertEquals(fechaCita, cita.getFecha());
        assertEquals(200000, cita.getTotalPago());
        assertEquals(1, cita.getCodigoServicio());
    }

    @Test
    void deberiaFallarSinCodigoMascota() {

        //Arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCodigoMascota(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de la mascota");
    }

    @Test
    void deberiaFallarSinCodigoServicio() {

        //Arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCodigoServicio(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de servicio");
    }

    @Test
    void deberiaFallarSinIdVeterinario() {

        //Arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conIdVeterinario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de veterinario");
    }


    @Test
    void deberiaFallarSinTotalPago() {

        //Arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conTotalPago(0).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Se debe ingresar el valor a pagar");
    }

    @Test
    void deberiaFallarSinFecha() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha");
    }

    @Test
    void deberiaFallarSiLaFechaEsFinDeSemana() {
        LocalDateTime diaFinDeSemana = LocalDateTime.of(2022, Month.MAY, 1, 10, 10, 30);

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFinDeSemana(diaFinDeSemana).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Lo sentimos, no hay servicio los fines de semana");
    }
}
