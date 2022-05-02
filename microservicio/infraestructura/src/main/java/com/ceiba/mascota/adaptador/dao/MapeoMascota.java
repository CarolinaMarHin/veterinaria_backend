package com.ceiba.mascota.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoMascota implements RowMapper<DtoMascota>, MapperResult {

    @Override
    public DtoMascota mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String codigoMascota = resultSet.getString("codigo_mascota");
        String nombreMascota = resultSet.getString("nombre_mascota");
        String raza = resultSet.getString("raza");
        LocalDate fechaNacimientoMascota = extraerLocalDate(resultSet, "fecha_nacimiento_mascota");
        int peso = resultSet.getInt("peso");

        return new DtoMascota(id, codigoMascota, nombreMascota, raza, fechaNacimientoMascota, peso);
    }

}
