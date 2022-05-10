package com.ceiba.servicio.adaptador.dao;

import com.ceiba.cita.adaptador.dao.MapeoCita;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.servicio.modelo.dto.DtoServicio;
import com.ceiba.servicio.puerto.dao.DaoServicio;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DaoServicioMysql implements DaoServicio {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "servicio", value = "listar")
    private static String sqlListarServicios;


    public DaoServicioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoServicio> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarServicios, new MapeoServicio());
    }
}
