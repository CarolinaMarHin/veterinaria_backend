package com.ceiba.mascota.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMacota;
import com.ceiba.usuario.modelo.entidad.Usuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMascotaMysql implements RepositorioMacota {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "mascota", value = "crear")
    private static String sqlCrearMascota;

    @SqlStatement(namespace = "mascota", value = "actualizar")
    private static String sqlActualizarMascota;

    @SqlStatement(namespace = "mascota", value = "eliminar")
    private static String sqlEliminarMascota;

    @SqlStatement(namespace = "mascota", value = "existe")
    private static String sqlExisteMascota;

    @SqlStatement(namespace = "mascota", value = "existePorId")
    private static String sqlExistePorIdMascota;

    public RepositorioMascotaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Mascota mascota) {
        return this.customNamedParameterJdbcTemplate.crear(mascota, sqlCrearMascota);
    }


    @Override
    public void actualizar(Mascota mascota) {
        this.customNamedParameterJdbcTemplate.actualizar(mascota, sqlActualizarMascota);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarMascota, paramSource);
    }

    @Override
    public boolean existe(String nombreMascota) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombreMascota", nombreMascota);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteMascota, paramSource, Boolean.class);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePorIdMascota, paramSource, Boolean.class);
    }
}
