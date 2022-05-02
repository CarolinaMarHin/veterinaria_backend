package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.mascota.modelo.entidad.Mascota;

public interface RepositorioMacota {

    Long crear(Mascota mascota);

    void actualizar(Mascota mascota);

    void eliminar(Long id);

    boolean existe(String codigoMascota);

    boolean existePorId(Long id);

}
