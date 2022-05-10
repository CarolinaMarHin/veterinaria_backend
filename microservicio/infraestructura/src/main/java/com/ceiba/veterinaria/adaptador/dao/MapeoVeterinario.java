package com.ceiba.veterinaria.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.veterinario.dto.DtoVeterinario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoVeterinario implements RowMapper<DtoVeterinario>, MapperResult {

    @Override
    public DtoVeterinario mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombreVeterinario = resultSet.getString("nombre_veterinario");
        String cedulaVeterinario = resultSet.getString("cedula_veterinario");

        return new DtoVeterinario(id, nombreVeterinario, cedulaVeterinario);
    }

}
