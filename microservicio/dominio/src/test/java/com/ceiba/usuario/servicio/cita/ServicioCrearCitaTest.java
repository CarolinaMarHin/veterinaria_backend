package com.ceiba.usuario.servicio.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.usuario.servicio.testdatabuilder.cita.CitaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ServicioCrearCitaTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando el veterinario tenga mas de 4 citas")
    void deberiaLanzarUnaExepcionCuandoVeterinarioTengaMasDeCuatroAlDia() {
        Cita cita = new CitaTestDataBuilder().build();
        List<DtoCita> dtoCitas = new ArrayList<>();
        for (int i = 0; i < 4 ; i ++) {
            DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina",
                    "Servicio banio", 130000, LocalDateTime.now());
            dtoCitas.add(dtoCita);
        }
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoMascota daoMascota = Mockito.mock(DaoMascota.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(repositorioCita.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoCita.listarCitasPorVeterinario(Mockito.eq(cita.getIdVeterinario()), Mockito.eq(cita.getFecha()))).thenReturn(dtoCitas);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, "El veterinario no puede ser asignado por que ya cumplio la cantidad permitida");
    }

    @Test
    @DisplayName("Deberia realizar descuento si es el cumpleaños de la mascota")
    void deberiaRealizarDescuentoPorCumpleañosDeLaMascota() {
        Cita cita = new CitaTestDataBuilder().build();

        DtoMascota dtoMascota = new DtoMascota(1L, "1234", "Titan", "Husky", LocalDate.now(), 30);
        Cita citaRespuesta = new Cita(1L, 1L, 1L,1L, 171000, LocalDateTime.now());
        DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina", "Servicio banio",
                171000, LocalDateTime.now());

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoMascota daoMascota = Mockito.mock(DaoMascota.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(repositorioCita.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoMascota.listarPorId(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoMascota);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        Long idCita = servicioCrearCita.ejecutar(cita);
        Mockito.when(daoCita.listarPorId(Mockito.eq(idCita))).thenReturn(dtoCita);
        assertEquals(citaRespuesta.getTotalPago(), dtoCita.getTotalPago());
    }

    @Test
    @DisplayName("Deberia dejar gratis el sexto baño")
    void deberiaDejarBañoSextoGratis() {
        Cita cita = new CitaTestDataBuilder().build();
        List<DtoCita> dtoCitas = new ArrayList<>();
        for (int i = 0; i < 5 ; i ++) {
            DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina",
                    "Servicio banio", 130000, LocalDateTime.now());
            dtoCitas.add(dtoCita);
        }
        DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina", "Servicio banio",
                0, LocalDateTime.now());
        DtoMascota dtoMascota = new DtoMascota(1L, "1234", "Titan", "Husky", LocalDate.now(), 30);
        Cita citaRespuesta = new Cita(1L, 1L, 1L,1L, 0, LocalDateTime.now());
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoMascota daoMascota = Mockito.mock(DaoMascota.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(repositorioCita.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoMascota.listarPorId(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoMascota);
        Mockito.when(daoCita.listarCitasPorIdMascota(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoCitas);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        Long idCita = servicioCrearCita.ejecutar(cita);
        Mockito.when(daoCita.listarPorId(Mockito.eq(idCita))).thenReturn(dtoCita);
        assertEquals(citaRespuesta.getTotalPago(), dtoCita.getTotalPago());
    }

    @Test
    @DisplayName("Deberia cobrar el banio")
    void deberiaCobrarBanio() {
        Cita cita = new CitaTestDataBuilder().build();
        List<DtoCita> dtoCitas = new ArrayList<>();
        for (int i = 0; i < 2 ; i ++) {
            DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina",
                    "Servicio banio", 130000, LocalDateTime.now());
            dtoCitas.add(dtoCita);
        }
        DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina", "Servicio banio",
                170000, LocalDateTime.now());
        DtoMascota dtoMascota = new DtoMascota(1L, "1234", "Titan", "Husky", LocalDate.now(), 30);
        Cita citaRespuesta = new Cita(1L, 1L, 1L,1L, 0, LocalDateTime.now());
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoMascota daoMascota = Mockito.mock(DaoMascota.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(repositorioCita.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoMascota.listarPorId(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoMascota);
        Mockito.when(daoCita.listarCitasPorIdMascota(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoCitas);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        Long idCita = servicioCrearCita.ejecutar(cita);
        Mockito.when(daoCita.listarPorId(Mockito.eq(idCita))).thenReturn(dtoCita);
        assertNotEquals(citaRespuesta.getTotalPago(), dtoCita.getTotalPago());
    }

    /*@Test
    @DisplayName("Deberia Crear la cita de manera correcta")
    void deberiaCrearLaCitaDeManeraCorrecta() {
        Cita cita = new CitaTestDataBuilder().build();
        List<DtoCita> dtoCitas = new ArrayList<>();
        DtoMascota dtoMascota = new DtoMascota(1L, "1234", "Titan", "Husky", LocalDate.now(), 30);
        DtoCita dtoCita = new DtoCita(1L, "Titan", "Carolina",
                "Servicio banio", 130000, LocalDateTime.now());
        dtoCitas.add(dtoCita);
        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        DaoMascota daoMascota = Mockito.mock(DaoMascota.class);
        DaoCita daoCita = Mockito.mock(DaoCita.class);
        Mockito.when(repositorioCita.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoMascota.listarPorId(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoMascota);
        Mockito.when(daoCita.listarCitasPorVeterinario(Mockito.eq(cita.getIdVeterinario()), Mockito.eq(cita.getFecha()))).thenReturn(dtoCitas);
        Mockito.when(repositorioCita.crear(cita)).thenReturn(0L);

        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        Long idCita = servicioCrearCita.ejecutar(cita);
        assertEquals(0L, idCita);
        Mockito.verify(repositorioCita, Mockito.times(1)).crear(cita);
    }*/
}
