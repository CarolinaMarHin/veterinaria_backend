package com.ceiba.usuario.servicio.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioActualizarCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.servicio.testdatabuilder.cita.CitaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarCitaTest {

    @Test
    @DisplayName("Deberia validar la existencia previa de la cita")
    void deberiaValidarLaExistenciaPreviaDeLaCita() {
        // arrange
        Cita cita = new CitaTestDataBuilder().conId(1L).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCita.ejecutar(cita), ExcepcionDuplicidad.class, "La cita no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Cita cita = new CitaTestDataBuilder().conId(1L).build();
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        Mockito.when(repositorioCita.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
        // act
        servicioActualizarCita.ejecutar(cita);
        //assert
        Mockito.verify(repositorioCita, Mockito.times(1)).actualizar(cita);
    }
}
