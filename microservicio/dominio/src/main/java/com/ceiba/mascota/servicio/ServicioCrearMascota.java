package com.ceiba.mascota.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;


public class ServicioCrearMascota {

    private static final String LA_MASCOTA_YA_EXISTE_EN_EL_SISTEMA = "La mascota ya existe en el sistema";

    private final RepositorioMascota repositorioUsuario;

    public ServicioCrearMascota(RepositorioMascota repositorioMascota) {
        this.repositorioUsuario = repositorioMascota;
    }

    public Long ejecutar(Mascota mascota) {
        validarExistenciaPrevia(mascota);
        return this.repositorioUsuario.crear(mascota);
    }

    private void validarExistenciaPrevia(Mascota mascota) {
        boolean existe = this.repositorioUsuario.existe(mascota.getCodigoMascota());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_MASCOTA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
