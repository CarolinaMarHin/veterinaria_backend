package com.ceiba.usuario.servicio.mascota;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioCrearMascota;
import com.ceiba.usuario.servicio.testdatabuilder.mascota.MascotaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearMascotaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la mascota")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaMascota() {
        Mascota mascota = new MascotaTestDataBuilder().build();
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearMascota servicioCrearMascota = new ServicioCrearMascota(repositorioMascota);
        BasePrueba.assertThrows(() -> servicioCrearMascota.ejecutar(mascota), ExcepcionDuplicidad.class, "La mascota ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el mascota de manera correcta")
    void deberiaCrearElMascotaDeManeraCorrecta() {
        Mascota mascota = new MascotaTestDataBuilder().build();
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        Mockito.when(repositorioMascota.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioMascota.crear(mascota)).thenReturn(10L);
        ServicioCrearMascota servicioCrearMascota = new ServicioCrearMascota(repositorioMascota);
        Long idMascota = servicioCrearMascota.ejecutar(mascota);
        assertEquals(10L, idMascota);
        Mockito.verify(repositorioMascota, Mockito.times(1)).crear(mascota);
    }
}
