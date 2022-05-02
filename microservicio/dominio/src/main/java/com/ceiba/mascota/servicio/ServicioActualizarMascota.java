package com.ceiba.mascota.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMacota;

public class ServicioActualizarMascota {

    private static final String LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "La macota no existe en el sistema";

    private final RepositorioMacota repositorioMacota;

    public ServicioActualizarMascota(RepositorioMacota repositorioMacota) {
        this.repositorioMacota = repositorioMacota;
    }

    public void ejecutar(Mascota mascota) {
        validarExistenciaPrevia(mascota);
        this.repositorioMacota.actualizar(mascota);
    }

    private void validarExistenciaPrevia(Mascota mascota) {
        boolean existe = this.repositorioMacota.existePorId(mascota.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
