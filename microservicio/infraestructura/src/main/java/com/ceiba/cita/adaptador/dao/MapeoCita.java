package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {

    @Override
    public DtoCita mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombreMascota = resultSet.getString("nombre_mascota");
        String nombreVeterinario = resultSet.getString("nombre_veterinario");
        String nombreServicio = resultSet.getString("nombre_servicio");
        double totalPago = resultSet.getDouble("total_pago");
        LocalDateTime fecha = extraerLocalDateTime(resultSet, "fecha");

        return new DtoCita(id, nombreMascota, nombreVeterinario, nombreServicio, totalPago, fecha);
    }

}
