package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarCitaPorId {

    private final DaoCita daoCita;

    public ManejadorListarCitaPorId(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public DtoCita ejecutar(Long id) {
        return this.daoCita.listarPorId(id);
    }
}
