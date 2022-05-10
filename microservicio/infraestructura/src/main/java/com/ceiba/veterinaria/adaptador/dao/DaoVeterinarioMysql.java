package com.ceiba.veterinaria.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.servicio.modelo.dto.DtoServicio;
import com.ceiba.servicio.puerto.dao.DaoServicio;
import com.ceiba.veterinario.dao.DaoVeterinario;
import com.ceiba.veterinario.dto.DtoVeterinario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoVeterinarioMysql implements DaoVeterinario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "veterinario", value = "listar")
    private static String sqlListarVeterinario;


    public DaoVeterinarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoVeterinario> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarVeterinario, new MapeoVeterinario());
    }
}
