package com.ceiba.cita.servicio;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioEliminarCita {

    private final RepositorioCita repositorioCita;

    public ServicioEliminarCita(RepositorioCita repositorioMascota) {
        this.repositorioCita = repositorioMascota;
    }

    public void ejecutar(Long id) {
        this.repositorioCita.eliminar(id);
    }
}
