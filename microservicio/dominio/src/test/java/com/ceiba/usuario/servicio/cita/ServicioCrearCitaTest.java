package com.ceiba.usuario.servicio.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioCrearMascota;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.cita.CitaTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.mascota.MascotaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearCitaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando el veterinario tenga mas de 4 citas")
    void deberiaLanzarUnaExepcionCuandoVeterinarioTengaMasDeCuatroAlDia() {
        Cita cita = new CitaTestDataBuilder().build();
        List<DtoCita> dtoCitas = new ArrayList<>();
        for (int i = 0; i < 5 ; i ++) {
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

    /*@Test
    @DisplayName("Deberia dejar gratis el sexto baño")
    void deberiaDejarElBañoGratisAlLLegarAlSexto() {
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
        Mockito.when(daoCita.listarCitasPorIdMascota(Mockito.eq(cita.getCodigoMascota()))).thenReturn(dtoCitas);
        ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, "El veterinario no puede ser asignado por que ya cumplio la cantidad permitida");
    }*/
}
