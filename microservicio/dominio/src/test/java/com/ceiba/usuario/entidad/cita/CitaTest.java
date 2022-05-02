package com.ceiba.usuario.entidad.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.servicio.testdatabuilder.cita.CitaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitaTest {

    @Test
    @DisplayName("Deberia crear correctamente la cita")
    void deberiaCrearCorrectamenteLaCita() {
        LocalDateTime fechaCita = LocalDateTime.now();
        Cita cita = new CitaTestDataBuilder().conFecha(fechaCita).conId(1L).build();
        assertEquals(1, cita.getId());
        assertEquals(1, cita.getCodigoMascota());
        assertEquals(1, cita.getIdVeterinario());
        assertEquals(fechaCita, cita.getFecha());
        assertEquals(200000, cita.getTotalPago());
        assertEquals(1, cita.getCodigoServicio());
    }

    @Test
    void deberiaFallarSinCodigoMascota() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCodigoMascota(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de la mascota");
    }

    @Test
    void deberiaFallarSinCodigoServicio() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCodigoServicio(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de servicio");
    }

    @Test
    void deberiaFallarSinIdVeterinario() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conIdVeterinario(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el codigo de veterinario");
    }

    @Test
    void deberiaFallarSinTotalPago() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conTotalPago(0).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Se debe ingresar el valor a pagar");
    }

    @Test
    void deberiaFallarSinFecha() {
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(null).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha");
    }

    @Test
    void deberiaFallarSiLaFechaEsUnSabado() {
        LocalDateTime diaFinDeSemana = LocalDateTime.of(2022, Month.APRIL, 30, 10, 10, 30);

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFinDeSemana(diaFinDeSemana).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Lo sentimos, no hay servicio los fines de semana");
    }

    @Test
    void deberiaFallarSiLaFechaEsUnDomingo() {
        LocalDateTime diaFinDeSemana = LocalDateTime.of(2022, Month.MAY, 1, 10, 10, 30);

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFinDeSemana(diaFinDeSemana).conId(1L);
        BasePrueba.assertThrows(() -> {
                    citaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Lo sentimos, no hay servicio los fines de semana");
    }
}
