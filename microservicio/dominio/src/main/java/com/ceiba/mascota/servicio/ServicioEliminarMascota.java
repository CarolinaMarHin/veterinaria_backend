package com.ceiba.mascota.servicio;

import com.ceiba.mascota.puerto.repositorio.RepositorioMacota;

public class ServicioEliminarMascota {

    private final RepositorioMacota repositorioMacota;

    public ServicioEliminarMascota(RepositorioMacota repositorioMacota) {
        this.repositorioMacota = repositorioMacota;
    }

    public void ejecutar(Long id) {
        this.repositorioMacota.eliminar(id);
    }
}
