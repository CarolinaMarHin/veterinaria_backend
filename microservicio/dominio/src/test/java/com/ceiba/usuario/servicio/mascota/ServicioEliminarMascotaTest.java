package com.ceiba.usuario.servicio.mascota;

import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioEliminarMascota;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarMascotaTest {

    @Test
    @DisplayName("Deberia eliminar el mascota llamando al repositorio")
    void deberiaEliminarElMascotaLlamandoAlRepositorio() {
        RepositorioMascota repositorioMascota = Mockito.mock(RepositorioMascota.class);
        ServicioEliminarMascota servicioEliminarMascota = new ServicioEliminarMascota(repositorioMascota);

        servicioEliminarMascota.ejecutar(1l);

        Mockito.verify(repositorioMascota, Mockito.times(1)).eliminar(1l);

    }

}
