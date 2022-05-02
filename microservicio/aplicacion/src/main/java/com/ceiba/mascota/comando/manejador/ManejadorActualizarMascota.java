package com.ceiba.mascota.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.fabrica.FabricaMascota;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.servicio.ServicioActualizarMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarMascota implements ManejadorComando<ComandoMascota> {

    private final FabricaMascota fabricaMascota;
    private final ServicioActualizarMascota servicioActualizarMascota;

    public ManejadorActualizarMascota(FabricaMascota fabricaMascota, ServicioActualizarMascota servicioActualizarMascota) {
        this.fabricaMascota = fabricaMascota;
        this.servicioActualizarMascota = servicioActualizarMascota;
    }

    public void ejecutar(ComandoMascota comandoUsuario) {
        Mascota mascota = this.fabricaMascota.crear(comandoUsuario);
        this.servicioActualizarMascota.ejecutar(mascota);
    }
}
