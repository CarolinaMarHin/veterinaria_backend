package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DaoCitaMysql implements DaoCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cita", value = "listar")
    private static String sqlListarCita;

    @SqlStatement(namespace = "cita", value = "listarPorId")
    private static String sqlListarCitaPorId;


    @SqlStatement(namespace = "cita", value = "listarCitasPorVeterinario")
    private static String sqllistarCitaPorVeterinario;

    @SqlStatement(namespace = "cita", value = "listarCitasPorIdMascota")
    private static String sqllistarCitasIdMascota;

    public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCita> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarCita, new MapeoCita());
    }

    @Override
    public List<DtoCita> listarCitasPorVeterinario(Long idVeterinario, LocalDate fecha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idVeterinario", idVeterinario);
        paramSource.addValue("fecha", fecha);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqllistarCitaPorVeterinario,
                paramSource, new MapeoCita());
    }

    @Override
    public List<DtoCita> listarCitasPorIdMascota(Long codigoMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoMascota", codigoMascota);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqllistarCitasIdMascota,
                paramSource, new MapeoCita());
    }

    @Override
    public DtoCita listarPorId(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlListarCitaPorId,
                paramSource, new MapeoCita());
    }
}
