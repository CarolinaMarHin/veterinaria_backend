package com.ceiba.mascota.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.ServicioCrearMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearMascota implements ManejadorComandoRespuesta<ComandoMascota, ComandoRespuesta<Long>> {

    private final FabricaMascota fabricaMascota;
    private final ServicioCrearMascota servicioCrearMascota;

    public ManejadorCrearMascota(FabricaMascota fabricaUsuario, ServicioCrearMascota servicioCrearMascota) {
        this.fabricaMascota = fabricaUsuario;
        this.servicioCrearMascota = servicioCrearMascota;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoMascota comandoMascota) {
        Mascota mascota = this.fabricaMascota.crear(comandoMascota);
        return new ComandoRespuesta<>(this.servicioCrearMascota.ejecutar(mascota));
    }
}
