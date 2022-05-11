package com.ceiba.mascota.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMascotaMysql implements DaoMascota {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "mascota", value = "listar")
    private static String sqlListarMascota;

    @SqlStatement(namespace = "mascota", value = "obtenerMascotaPorId")
    private static String sqlListarMascotaPorId;

    @SqlStatement(namespace = "mascota", value = "obtenerMascota")
    private static String sqlListarMascotaPorCodigo;

    public DaoMascotaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoMascota> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarMascota, new MapeoMascota());
    }

    @Override
    public DtoMascota listarPorId(Long idMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idMascota", idMascota);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlListarMascotaPorId,
                paramSource, new MapeoMascota());
    }

    @Override
    public DtoMascota listarPorCodigoMascota(String codigoMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("codigoMascota", codigoMascota);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlListarMascotaPorCodigo,
                paramSource, new MapeoMascota());
    }
}
