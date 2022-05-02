package com.ceiba.mascota.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

public class ServicioActualizarMascota {

    private static final String LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA = "La mascota no existe en el sistema";

    private final RepositorioMascota repositorioMascota;

    public ServicioActualizarMascota(RepositorioMascota repositorioMascota) {
        this.repositorioMascota = repositorioMascota;
    }

    public void ejecutar(Mascota mascota) {
        validarExistenciaPrevia(mascota);
        this.repositorioMascota.actualizar(mascota);
    }

    private void validarExistenciaPrevia(Mascota mascota) {
        boolean existe = this.repositorioMascota.existePorId(mascota.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_MASCOTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
