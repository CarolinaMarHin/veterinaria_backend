package com.ceiba.veterinario.consulta;

import com.ceiba.veterinario.dao.DaoVeterinario;
import com.ceiba.veterinario.dto.DtoVeterinario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarVeterinario {

    private final DaoVeterinario daoVeterinario;

    public ManejadorListarVeterinario(DaoVeterinario daoVeterinario) {
        this.daoVeterinario = daoVeterinario;
    }

    public List<DtoVeterinario> ejecutar() {
        return this.daoVeterinario.listar();
    }
}
