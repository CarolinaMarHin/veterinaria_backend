package com.ceiba.mascota.consulta;

import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarMascotasCodigoMascota {

    private final DaoMascota daoMascota;

    public ManejadorListarMascotasCodigoMascota(DaoMascota daoMascota) {
        this.daoMascota = daoMascota;
    }

    public DtoMascota ejecutar(String codigoMascota) {
        return this.daoMascota.listarPorCodigoMascota(codigoMascota);
    }
}
