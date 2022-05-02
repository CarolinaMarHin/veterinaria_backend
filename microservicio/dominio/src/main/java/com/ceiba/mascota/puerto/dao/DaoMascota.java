package com.ceiba.mascota.puerto.dao;

import com.ceiba.mascota.modelo.dto.DtoMascota;

import java.util.List;

public interface DaoMascota {

    List<DtoMascota> listar();

    DtoMascota listarPorId(Long id);

    DtoMascota listarPorCodigoMascota(String codigoMascota);
}
