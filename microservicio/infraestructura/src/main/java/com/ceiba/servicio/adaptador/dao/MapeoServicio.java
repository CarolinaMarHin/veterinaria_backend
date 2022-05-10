package com.ceiba.servicio.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.servicio.modelo.dto.DtoServicio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoServicio implements RowMapper<DtoServicio>, MapperResult {

    @Override
    public DtoServicio mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombreServicio = resultSet.getString("nombre_servicio");
        Double precioServicio = resultSet.getDouble("precio_servicio");

        return new DtoServicio(id, nombreServicio, precioServicio);
    }

}
