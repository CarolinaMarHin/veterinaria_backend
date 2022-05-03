package com.ceiba.mascota.consulta;

import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarMascotasPorId {

    private final DaoMascota daoMascota;

    public ManejadorListarMascotasPorId(DaoMascota daoMascota) {
        this.daoMascota = daoMascota;
    }

    public DtoMascota ejecutar(Long idMascota) {
        return this.daoMascota.listarPorId(idMascota);
    }
}
